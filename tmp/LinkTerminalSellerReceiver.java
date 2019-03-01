package stelo.accreditation;

import br.com.stelo.domain.comum.Cabecalho;
import br.com.stelo.domain.ec.MaquinaCartao;
import br.com.stelo.domain.pedidos.Pedido;
import br.com.stelo.domain.pessoas.Pessoa;
import br.com.stelo.domain.pessoas.PessoaFisica;
import br.com.stelo.domain.pessoas.PessoaJuridica;
import br.com.stelo.services.cadu.precadastrompos.v1.AssociarVendedorAoMPOS;
import br.com.stelo.services.cadu.precadastrompos.v1.AssociarVendedorAoMPOSFault;
import br.com.stelo.services.cadu.precadastrompos.v1.PreCadastroContract;
import br.com.stelo.services.cadu.precadastrompos.v1.PreCadastroContractService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import stelo.accreditation.integration.credenciamento.cielo.CredenciamentoCieloApiService;
import stelo.accreditation.integration.credenciamento.cielo.request.AssociarVendedorMposCieloRequest;
import stelo.accreditation.integration.credenciamento.cielo.request.Auditoria;
import stelo.accreditation.integration.credenciamento.cielo.request.Maquina;
import stelo.accreditation.util.CieloOrderProductTrackingNotFoundException;
import stelo.accreditation.util.SteloBusinessException;
import stelo.accreditation.util.TipoSoftAdquirente;
import stelo.data.TipoPessoa;
import stelo.data.cdto.ModeloMaquina;
import stelo.data.cdto.ModeloMaquinaRepository;
import stelo.data.trng.Order;
import stelo.data.trng.OrderProductTracking;
import stelo.data.trng.OrderProductTrackingRepository;
import stelo.data.trng.OrderRepository;
import stelo.data.trng.OrderTracking;
import stelo.data.trng.OrderTrackingRepository;
import stelo.data.trng.SellTerminal;
import stelo.data.trng.SellTerminalOrder;
import stelo.data.trng.SellTerminalOrderRepository;
import stelo.data.trng.SellTerminalRepository;
import stelo.data.trng.TerminalRequestControl;
import stelo.data.trng.TerminalRequestControlRepository;
import stelo.data.trng.TerminalRequestStatus;
import stelo.data.trng.TerminalSellItem;
import stelo.data.trng.TerminalSellItemRepository;
import stelo.service.OrderService;
import stelo.service.MessageService;
import stelo.util.SteloLogger;
import stelo.util.TrackTime;

@Component
public class LinkTerminalSellerReceiver {
  private static final String LINK_TERMINAL_SELLER_JOB = "link-terminal-seller-job";
  private static final String BACKEND = "BACKEND";

  private static final SteloLogger<LinkTerminalSellerReceiver> LOG =
      new SteloLogger<>(LinkTerminalSellerReceiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  @Autowired OrderService orderService;

  @Autowired MessageService messageService;

  @Autowired CredenciamentoCieloApiService credenciamentoCieloApiService;

  @Autowired TerminalSellItemRepository terminalSellItemRepository;

  @Autowired OrderTrackingRepository orderTrackingRepository;

  @Autowired OrderProductTrackingRepository orderProductTrackingRepository;

  @Autowired SellTerminalOrderRepository sellTerminalOrderRepository;

  @Autowired SellTerminalRepository sellTerminalRepository;

  @Autowired ModeloMaquinaRepository modeloMaquinaRepository;

  @Autowired OrderRepository orderRepository;

  @Autowired TerminalRequestControlRepository terminalRequestControlRepository;

  @Value("${precadastro.wsdl.location}")
  String preCadastroMPOSLocation;

  private static final String MDCNAME = "mdcData";

  private static final String LINK_2500_BRSTL = "LINK/2500-BRSTL";

  @TrackTime
  public void receiveMessage(String message) {
    String messageUid = "" + UUID.randomUUID();
    String mdcData = String.format("[%s]", "" + messageUid);
    MDC.put(MDCNAME, mdcData);
    String orderId = null;
    String serialNumber = null;
    BigDecimal codMaqna = null;
    String partNumber = null;

    try {
      LOG.info("Message received: [%s]", message);
      JSONObject json = new JSONObject(message);
      orderId = json.getString("orderId");
      serialNumber = json.getString("serialNumber");
      partNumber = json.getString("partNumber");
      Optional<Order> optOrder = orderService.findOrderById(new Long(orderId));
      Order order;
      ModeloMaquina modMaqna = null;
      if (optOrder.isPresent()) {
        order = optOrder.get();

        // TB_VDA_MAQNA_PDIDO
        SellTerminalOrder sellTerminalOrder = getSellTerminalOrderByOrderId(orderId);

        // TB_VDA_MAQNA
        SellTerminal sellTerminal = getSellTerminalBySellTerminalOrder(sellTerminalOrder);

        // TB_PDIDO_TRACK
        OrderTracking orderTracking = getOrderTrackingByOrderId(orderId);

        if (TipoSoftAdquirente.GSURF
            .getDesc()
            .equalsIgnoreCase(sellTerminal.getTipoSoftAdquirente())) {

          // TB_PDIDO_TRACK_PRODT
          OrderProductTracking orderProductTracking =
              getOrderProductTrackingByOrderTrackingAndSerial(orderTracking, serialNumber);

          // Cod Maqna (Se partNumber iniciar com LINK/2500-BRSTL ignora os caracteres referente à
          // operadora)
          if (StringUtils.isNotBlank(partNumber) && partNumber.startsWith(LINK_2500_BRSTL)) {
            codMaqna = getCodMaqnaByPartNumber(LINK_2500_BRSTL);
          } else {
            codMaqna = getCodMaqnaByPartNumber(partNumber);
          }

          // Modelo Maquina
          modMaqna = getModeloMaquinaById(codMaqna);

          Optional<TerminalSellItem> optTerminalSellItem =
              terminalSellItemRepository.findFirstBySellTerminalIdAndSerialNumber(
                  sellTerminalOrder.getIdVdaMaqna(), serialNumber);
          TerminalSellItem terminalSellItem = null;
          if (!optTerminalSellItem.isPresent()) {
            Optional<BigDecimal> optIdOper =
                modeloMaquinaRepository.findPhoneOperatorIdByPhoneOperatorName(
                    orderProductTracking.getMobileOperatorName());
            if (optIdOper.isPresent()) {
              optTerminalSellItem =
                  terminalSellItemRepository.findFirstBySellTerminalIdAndOperadoraAndMaquina(
                      sellTerminalOrder.getIdVdaMaqna(),
                      optIdOper.get().longValue(),
                      codMaqna.longValue());
              if (!optTerminalSellItem.isPresent()) {
                throw new SteloBusinessException(
                    "Nenhum registro de item vendido para a venda id ["
                        + sellTerminalOrder.getIdVdaMaqna()
                        + "], serialNumber ["
                        + serialNumber
                        + "] e nem partNumber ["
                        + partNumber
                        + "]");
              }
            } else {
              throw new SteloBusinessException(
                  "Operadora não encontrada ["
                      + orderProductTracking.getMobileOperatorName()
                      + "]");
            }

            terminalSellItem = optTerminalSellItem.get();
            terminalSellItem.setSerialNumber(serialNumber);
            BigDecimal modelValue =
                new BigDecimal(0)
                    .add(new BigDecimal(terminalSellItem.getValorTotal()))
                    .divide(
                        new BigDecimal(terminalSellItem.getQuantidade()), 2, RoundingMode.HALF_UP)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);

            associarVendedorMposGsurf(
                order, serialNumber, modMaqna, modelValue, orderProductTracking.getChipNumber());
          }
        } else if (TipoSoftAdquirente.CIELO
            .getDesc()
            .equalsIgnoreCase(sellTerminal.getTipoSoftAdquirente())) {

          if (order.getTerminalRequestStatus() == TerminalRequestStatus.ASSOCIADO) {
            throw new SteloBusinessException(
                "Os seriais do pedido [" + orderId + "], já foram associados ao EC anteriormente");
          }

          List<TerminalSellItem> listTerminalSellItem =
              terminalSellItemRepository.findBySellTerminalId(sellTerminal.getId());

          List<OrderProductTracking> listOrderProductTracking =
              orderProductTrackingRepository.findByOrderTrackingId(
                  orderTracking.getOrderTrackingId());

          List<Maquina> listMaquinaCielo = new ArrayList<>();

          for (TerminalSellItem terminalSellItem : listTerminalSellItem) {

            BigDecimal modelValue =
                new BigDecimal(0)
                    .add(new BigDecimal(terminalSellItem.getValorTotal()))
                    .divide(
                        new BigDecimal(terminalSellItem.getQuantidade()), 2, RoundingMode.HALF_UP)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);

            for (int i = 0; i < terminalSellItem.getQuantidade(); i++) {

              ModeloMaquina modMaqnaItem =
                  getModeloMaquinaById(BigDecimal.valueOf(terminalSellItem.getMaquina()));

              Optional<OrderProductTracking> optOrderProductTracking =
                  listOrderProductTracking.stream()
                      .filter(p -> p.getPartNumber().startsWith(modMaqnaItem.getSku()))
                      .findFirst();

              if (!optOrderProductTracking.isPresent()) {
                throw new CieloOrderProductTrackingNotFoundException(
                    "Aguardando recebimento de todos os seriais do pedido. OrderId ["
                        + orderId
                        + "], serialNumber ["
                        + serialNumber
                        + "], partNumber ["
                        + partNumber
                        + "]");
              }

              OrderProductTracking orderProductTracking = optOrderProductTracking.get();

              Maquina m = new Maquina();
              m.setChip(orderProductTracking.getChipNumber());
              m.setCodigoModelo(modMaqnaItem.getCdModGsurf());
              m.setModelo(modMaqnaItem.getDescription());
              m.setNumeroSerie(orderProductTracking.getSerialNumber());
              m.setValor(modelValue.doubleValue());

              listMaquinaCielo.add(m);

              listOrderProductTracking.remove(orderProductTracking);
            }
          }

          associarVendedorMposCielo(order, listMaquinaCielo);

        } else {
          throw new SteloBusinessException(
              "Tipo Software Adquirente não identificado. OrderId["
                  + order.getId()
                  + "], idVdaMaqna["
                  + sellTerminal.getId()
                  + "], tipoSoftAdq["
                  + sellTerminal.getTipoSoftAdquirente()
                  + " ]");
        }

        order.setTerminalRequestStatus(TerminalRequestStatus.ASSOCIADO);
        order.setDataAlteracao(LocalDateTime.now());
        order.setUsuarioAlteracao(LINK_TERMINAL_SELLER_JOB);
        orderRepository.save(order);

        saveTerminalRequestControl(
            orderId, "Associado com sucesso.", 1, 2, serialNumber, null, null);

        LOG.info("Associado com sucesso: [%s]", message);

      } else {
        LOG.error(
            "Pedido não encontrado: [" + message + "]", new RuntimeException("orderId not found"));
      }
    } catch (JSONException e) {
      LOG.error("Invalid message received: [" + message + "]", e);

      messageService.send(message, e.getMessage());

    } catch (CieloOrderProductTrackingNotFoundException e) {
      LOG.info(e.getMessage());

      messageService.send(message, e.getMessage());

    } catch (SteloBusinessException e) {
      LOG.info(e.getMessage());

      messageService.send(message, e.getMessage());

      saveTerminalRequestControl(orderId, e.getMessage(), 0, 3, serialNumber, null, null);

    } catch (Exception e) {
      String errMessage = "Fail to consume message: " + e.getMessage();
      LOG.error(errMessage, e);

      messageService.send(message, e.getMessage());

      saveTerminalRequestControl(orderId, errMessage, 0, 3, serialNumber, null, null);
    }
    MDC.clear();
  }

  private SellTerminal getSellTerminalBySellTerminalOrder(SellTerminalOrder sellTerminalOrder) {
    Optional<SellTerminal> optSellTerminal =
        sellTerminalRepository.findById(sellTerminalOrder.getIdVdaMaqna());
    if (!optSellTerminal.isPresent()) {
      throw new SteloBusinessException(
          "Erro ao obter Tipo Software Adquirente: Venda Máquina não encontrada. OrderId["
              + sellTerminalOrder.getOrderId()
              + "], idVdaMaqna["
              + sellTerminalOrder.getIdVdaMaqna()
              + "]");
    }
    return optSellTerminal.get();
  }

  private ModeloMaquina getModeloMaquinaById(BigDecimal codMaqna) {
    Optional<ModeloMaquina> optModMaqna = modeloMaquinaRepository.findById(codMaqna.longValue());
    if (!optModMaqna.isPresent()) {
      throw new SteloBusinessException(
          "Não foi possível identificar o modelo da máquina através do código ["
              + codMaqna.longValue()
              + "]");
    }
    return optModMaqna.get();
  }

  private BigDecimal getCodMaqnaByPartNumber(String partNumber) {
    Optional<BigDecimal> optCodMaqna =
        modeloMaquinaRepository.findCodMaqnaByIngenicoPartNumber(partNumber);
    if (!optCodMaqna.isPresent()) {
      throw new SteloBusinessException(
          "Não foi possível identificar o código do modelo da máquina usando o partNumber ["
              + partNumber
              + "]");
    }
    return optCodMaqna.get();
  }

  private OrderProductTracking getOrderProductTrackingByOrderTrackingAndSerial(
      OrderTracking orderTracking, String serialNumber) {
    Optional<OrderProductTracking> optOrderProductTracking =
        orderProductTrackingRepository.findByOrderTrackingIdAndSerialNumber(
            orderTracking.getOrderTrackingId(), serialNumber);
    if (!optOrderProductTracking.isPresent()) {
      // We have no other way to detect required data, so let's abort.
      throw new SteloBusinessException(
          "Serial do terminal não encontrado nas tabelas de rastreio. OrderId["
              + orderTracking.getOrderId()
              + "], serialNumber["
              + serialNumber
              + "]");
    }
    return optOrderProductTracking.get();
  }

  private OrderTracking getOrderTrackingByOrderId(String orderId) {
    Optional<OrderTracking> optOrderTracking =
        orderTrackingRepository.findByOrderId(Long.parseLong(orderId));
    if (!optOrderTracking.isPresent()) {
      throw new SteloBusinessException(
          "Nenhum registro de rastreio encontrado para o pedido [" + orderId + "]");
    }
    return optOrderTracking.get();
  }

  private SellTerminalOrder getSellTerminalOrderByOrderId(String orderId) {
    Optional<SellTerminalOrder> optSellTerminalOrder =
        sellTerminalOrderRepository.findFirstByOrderId(Long.parseLong(orderId));
    if (!optSellTerminalOrder.isPresent()) {
      throw new SteloBusinessException(
          "Nenhum registro encontrado na tabela de vínculo entre a Venda Maquina e o pedido para o pedido ["
              + orderId
              + "]");
    }
    return optSellTerminalOrder.get();
  }

  private void saveTerminalRequestControl(
      String orderId,
      String description,
      Integer statusExecution,
      Integer sentStatus,
      String serialNumber,
      String modelName,
      String uuid) {
    TerminalRequestControl terminalRequestControl = new TerminalRequestControl();
    terminalRequestControl.setOrderId(Long.parseLong(orderId));
    terminalRequestControl.setSentDate(LocalDateTime.now());
    terminalRequestControl.setDescription(StringUtils.substring(description, 0, 1999));
    terminalRequestControl.setInsertUser(LINK_TERMINAL_SELLER_JOB);
    terminalRequestControl.setInsertDate(LocalDateTime.now());
    terminalRequestControl.setStatusExecution(statusExecution);
    terminalRequestControl.setIdSentStatus(sentStatus);
    terminalRequestControl.setSerialNumber(StringUtils.substring(serialNumber, 0, 39));
    terminalRequestControl.setModelName(StringUtils.substring(modelName, 0, 149));
    terminalRequestControl.setUuidTransaction(uuid);
    terminalRequestControlRepository.save(terminalRequestControl);
  }

  private void associarVendedorMposGsurf(
      Order order, String serialNumber, ModeloMaquina modMaqna, BigDecimal modelValue, String chip)
      throws Exception {

    final Cabecalho header = new Cabecalho();
    header.setAplicacao(BACKEND);
    header.setUsuario(BACKEND);
    header.setCanal(BACKEND);

    // Pessoa
    Pessoa pessoa;
    if (TipoPessoa.FISICA == order.getTipoPessoaComprador()) {
      PessoaFisica pf = new PessoaFisica();
      pf.setCpf(order.getNumeroDocumentoComprador());
      pessoa = pf;
    } else {
      PessoaJuridica pj = new PessoaJuridica();
      pj.setCnpj(order.getNumeroDocumentoComprador());
      pessoa = pj;
    }

    // Maquina Cartão
    MaquinaCartao maquina = new MaquinaCartao();
    maquina.setCodigoModelo(BigInteger.valueOf(modMaqna.getCdModGsurf()));
    if (serialNumber.length() > 8) {
      maquina.setNumeroSerie(serialNumber.substring(serialNumber.length() - 8));
    } else {
      maquina.setNumeroSerie(serialNumber);
    }
    maquina.setValor(modelValue.doubleValue());
    maquina.setModelo(modMaqna.getDescription());
    maquina.setChip(chip);

    // Pedido
    Pedido pedido = new Pedido();
    pedido.setCodigoPedido(order.getId());

    AssociarVendedorAoMPOS associacaoAoVendedorMPOS = new AssociarVendedorAoMPOS();
    associacaoAoVendedorMPOS.setPessoa(pessoa);
    associacaoAoVendedorMPOS.setMaquinaCartao(maquina);
    associacaoAoVendedorMPOS.setPedido(pedido);

    try {
      PreCadastroContractService service = new PreCadastroContractService(new URL(preCadastroMPOSLocation));
      PreCadastroContract precadastro = service.getPreCadastroContractPort();
      precadastro.associarVendedorAoMPOS(associacaoAoVendedorMPOS, header);
    } catch (AssociarVendedorAoMPOSFault e) {
      throw new RuntimeException(
          "Erro ao associar serial: [{\"serialNumber\":\""
              + serialNumber
              + "\",\"msg\":\""
              + e.getMessage()
              + "\"}]",
          e);
    } catch (Exception e) {
      throw new RuntimeException(
          "Erro ao associar serial: [{\"serialNumber\":\""
              + serialNumber
              + "\",\"msg\":\""
              + e.getMessage()
              + "\"}]",
          e);
    }
  }

  private void associarVendedorMposCielo(Order order, List<Maquina> maquinas) throws Exception {

    stelo.accreditation.integration.credenciamento.cielo.request.Pessoa pessoa =
        new stelo.accreditation.integration.credenciamento.cielo.request.Pessoa();
    pessoa.setDocumento(order.getNumeroDocumentoComprador());
    pessoa.setTipoDocumento(order.getTipoPessoaComprador().getSigla());

    Auditoria auditoria = new Auditoria();
    auditoria.setAplicacao(LINK_TERMINAL_SELLER_JOB);

    AssociarVendedorMposCieloRequest request = new AssociarVendedorMposCieloRequest();
    request.setCodigoPedido(order.getId());
    request.setPessoa(pessoa);
    request.setMaquinas(maquinas);
    request.setAuditoria(auditoria);

    try {
      credenciamentoCieloApiService.associarVendedorMposCielo(request);
    } catch (HttpStatusCodeException e) {
      String seriais =
          maquinas.stream().map(Maquina::getNumeroSerie).collect(Collectors.joining("|"));
      throw new RuntimeException(
          "Erro ao associar serial: [{\"serialNumber\":\""
              + seriais
              + "\",\"msg\":\""
              + e.getResponseBodyAsString()
              + "\"}]",
          e);

    } catch (Exception e) {
      String seriais =
          maquinas.stream().map(Maquina::getNumeroSerie).collect(Collectors.joining("|"));
      throw new RuntimeException(
          "Erro ao associar serial: [{\"serialNumber\":\""
              + seriais
              + "\",\"msg\":\""
              + e.getMessage()
              + "\"}]",
          e);
    }
  }
}
