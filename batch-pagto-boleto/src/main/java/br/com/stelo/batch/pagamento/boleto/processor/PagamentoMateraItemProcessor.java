package br.com.stelo.batch.pagamento.boleto.processor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.stelo.batch.helper.MensagemDomain;
import br.com.stelo.batch.helper.PagamentoStatusType;
import br.com.stelo.batch.helper.SubMotivoFonteRejeicao;
import br.com.stelo.batch.pagamento.boleto.model.Moeda;
import br.com.stelo.batch.pagamento.boleto.model.Pagamento;
import br.com.stelo.batch.pagamento.boleto.model.PagamentoMatera;
import br.com.stelo.batch.pagamento.boleto.model.PagamentoMateraProc;
import br.com.stelo.batch.pagamento.boleto.model.Parcelamento;
import br.com.stelo.batch.pagamento.boleto.model.Pedido;
import br.com.stelo.batch.pagamento.boleto.model.PedidoOrigemType;
import br.com.stelo.batch.pagamento.boleto.model.Pessoa;
import br.com.stelo.batch.pagamento.boleto.model.TipoPagamentoType;
import br.com.stelo.batch.pagamento.boleto.model.TipoRegistroMatera;
import br.com.stelo.batch.pagamento.boleto.model.Transacao;
import br.com.stelo.batch.pagamento.boleto.model.Venda;
import br.com.stelo.batch.pagamento.boleto.repository.dao.IInconsistenciaBoletoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.dao.IPagamentoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.dao.IPedidoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.dao.ITerminalDAO;
import br.com.stelo.batch.pagamento.boleto.repository.dao.ITransacaoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.dao.IVendaDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.InconsistenciaBoletoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PagamentoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.TerminalEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.TransacaoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.VendaEntity;

public class PagamentoMateraItemProcessor implements ItemProcessor<PagamentoMatera, PagamentoMateraProc> {

	private static final Logger log = LoggerFactory.getLogger(PagamentoMateraItemProcessor.class);

	private static final MensagemDomain PEDIDO_PAGO = new MensagemDomain("ERRBT0007");
	private static final MensagemDomain NAO_ENCONTRADO = new MensagemDomain("ERRBT0002");
	private static final MensagemDomain PGTO_CANCELADO = new MensagemDomain("ERRBT0003");
	private static final MensagemDomain PGTO_MENOR = new MensagemDomain("ERRBT0005");
	private static final MensagemDomain PGTO_VENCIDO = new MensagemDomain("ERRBT0006");
	private static final MensagemDomain ERRO_SISTEMICO = new MensagemDomain("ERRBT0001");

	@Autowired
	private IPagamentoDAO pagamentoDAO;

	@Autowired
	private IPedidoDAO pedidoDAO;

	@Autowired
	private IVendaDAO vendaDAO;

	@Autowired
	private ITerminalDAO terminalDAO;

	@Autowired
	private ITransacaoDAO transacaoDAO;
	
	@Autowired
	private IInconsistenciaBoletoDAO inconsistenciaBoletoDAO;

	@Override
	public PagamentoMateraProc process(final PagamentoMatera pagamentoMatera) throws Exception {
		PagamentoMateraProc pagamentoMateraProc = null;
		if (TipoRegistroMatera.DETALHE.equals(pagamentoMatera.getTipoRegistro())) {

			PagamentoEntity pagamentoEntity = null;
			PedidoEntity pedidoEntity = null;
			VendaEntity vendaEntity = null;

			try {

				log.debug("### idstelo="+pagamentoMatera.getIdStelo()+" codpedido="+pagamentoMatera.getCodigoPedido());
				
				pagamentoEntity = pagamentoDAO.getPagamento(pagamentoMatera.getIdStelo(),
						pagamentoMatera.getCodigoPedido());

				pedidoEntity = pedidoDAO.getPedido(pagamentoMatera.getCodigoPedido());

				vendaEntity = vendaDAO.getVenda(pagamentoMatera.getIdStelo(), pagamentoMatera.getCodigoPedido());

				if (pagamentoEntity == null || pedidoEntity == null || vendaEntity == null) {
					log.info("Pedido [{}] nao encontrado, Gerando inconsistencia...",
							pagamentoMatera.getCodigoPedido());
					geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
							SubMotivoFonteRejeicao.PAGAMENTO_NAO_ENCONTRADO, NAO_ENCONTRADO);
				} else {

					final int COMPARAR_VALOR = Double.compare(pagamentoMatera.getValor(), pagamentoEntity.getVR_PGTO());

					if (PagamentoStatusType.CANCELADA.getValue().equals(pagamentoEntity.getCD_STTUS_PGTO())
							|| PagamentoStatusType.EMCANCELAMENTO.getValue()
									.equals(pagamentoEntity.getCD_STTUS_PGTO())) {
						log.info("Pagamento Cancelado ou Em Cancelamento, Gerando inconsistencia...");
						geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
								SubMotivoFonteRejeicao.PAGAMENTO_DE_PEDIDO_CANCELADO, PGTO_CANCELADO);
					} else if (PagamentoStatusType.APROVADA.getValue().equals(pagamentoEntity.getCD_STTUS_PGTO())) {
						log.info("Pagamento Duplicado, Gerando inconsistencia...");
						geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
								SubMotivoFonteRejeicao.PAGAMENTO_DUPLICADO, PEDIDO_PAGO);
					} else if (COMPARAR_VALOR < 0) {
						log.info("Pagamento com valor menor, Gerando inconsistencia...");
						geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
								SubMotivoFonteRejeicao.A_MENOR, PGTO_MENOR);
						cancelaPagamento(pagamentoMatera, pagamentoEntity, pedidoEntity,
								SubMotivoFonteRejeicao.A_MENOR);
					} else if (isPagamentoRealizadoAposVencimento(pagamentoMatera, pagamentoEntity)) {
						log.info("Pagamento vencido, Gerando inconsistencia...");
						geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
								SubMotivoFonteRejeicao.VENCIDO, PGTO_VENCIDO);
						cancelaPagamento(pagamentoMatera, pagamentoEntity, pedidoEntity,
								SubMotivoFonteRejeicao.VENCIDO);
					} else {
						processaPagamentoAprovado(pagamentoMatera, pagamentoEntity, vendaEntity, pedidoEntity);
					}
					// preencheArquivoProcessadoMatera(pagamentoMatera, dadosPagamento, vendaDomain,
					// pedidoDomain, arquivoProcessadoBatch);
					// carregaArquivoProcessadoBatchNoJobExecutionContext(arquivoProcessadoBatch);

					pagamentoMateraProc = PagamentoMateraProc.builder().status("11111").numeroPedidoEC("22222 ")
							.pedidoStelo("33333").codigoVendedor("44444").codigoPagamento("55555").valorBoleto("66666")
							.dataVencimento("77777").origemPedido("88888").codigoComprador("99999").cpf("00000")
							.emailComprador("11111").tipoRegistro(pagamentoMatera.getTipoRegistro()).build();
				}
			} catch (Exception ex) {
				log.error("Erro inesperado com o registro :" + pagamentoMatera, ex);
				geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
						SubMotivoFonteRejeicao.ERRO_SISTEMICO, ERRO_SISTEMICO);
			}

		}
		return pagamentoMateraProc;
	}

//----------------------	
	
	private final Map<Long, Long> terminaisConsultados = new HashMap<Long, Long>();
	private static final String CODIGO_DEFAULT_TERMINAL = "0";
	private static final String NUMERO_DEFAULT_TERMINAL = "0";
	private static final String ORIGEM_SUBADQUIRENCIA = "S";
	private static final String ORIGEM_M1 = "M1";
	private static final long ID_TECNOLOGIA_SUBADQUIRENCIA = 11l;
	private static final Object ORIGEM_MPOS = "M";
	private static final long ID_TECNOLOGIA_MPOS = 8l;
	private static final String ORIGEM_WALLET = "W";
	private static final Long ID_TECNOLOGIA_WALLET = 3l;
	private static final int TIPO_MSG_COMPRA = 220;
	private static final int TIPO_MSG_CANCELAMENTO = 420;
	private static final String CODIGO_MOEDA = "986";

	
	private void processaPagamentoAprovado(final PagamentoMatera pagamentoMatera,
			   final PagamentoEntity pagamentoEntity,
			   final VendaEntity vendaEntity,
			   final PedidoEntity pedidoEntity) throws Exception {
       pagamentoEntity.setCD_STTUS_PGTO(PagamentoStatusType.APROVADA.getValue());
       pagamentoEntity.setDT_PGTO(new Date());
       pagamentoEntity.setVR_PG(pagamentoMatera.getValor());
       pedidoEntity.setCD_STTUS_PDIDO(PagamentoStatusType.APROVADA.getValue());

       insereHistoricoTransacao( pagamentoEntity,  vendaEntity,  pedidoEntity);
       
       log.info("Registro processado com sucesso! " + pagamentoMatera);
       log.info("Pagamento OK - Marcado para incluir transação e gerar TIFF nos steps seguintes...");
    }

	public void insereHistoricoTransacao(PagamentoEntity pagamentoEntity, VendaEntity vendaEntity, PedidoEntity pedidoEntity) throws Exception {

		try {
	
	
			final Transacao paramTransacao = preencheTransacao(pagamentoEntity, pedidoEntity, vendaEntity);
			final Pedido paramPedido = preenchePedido(vendaEntity, pedidoEntity);
			final Pagamento paramPagamento = preenchePagamento(pagamentoEntity);
			final Parcelamento paramParcelamento = preencheParcelamento(pagamentoEntity);
			final Pessoa paramPessoa = preencheVendedor(vendaEntity.getCD_VDDOR());
			
			TransacaoEntity transacaoEntity = buildTrancacaoEntity(paramTransacao, paramPedido, paramPagamento, paramParcelamento, paramPessoa);
			transacaoDAO.inserir(transacaoEntity);
			
		} catch (final Exception e) {
			log.error("Erro ao inserir historico de transacao:", e);
			throw new Exception("ERRPDD0073", e);
		}
	}	
	
	public TransacaoEntity buildTrancacaoEntity(final Transacao transacao,
			final Pedido pedido,
			final Pagamento pagamento,
			final Parcelamento parcelamento,
			final Pessoa vendedor) {

			final Calendar c = Calendar.getInstance();
			c.setTime(transacao.getDataTransacao());
			c.set(Calendar.MILLISECOND, 0);

			final TransacaoEntity transacaoEntity = new TransacaoEntity();
			transacaoEntity.setDT_TRANS(c.getTime());
			transacaoEntity.setCD_TRANS(transacao.getCodigoTransacao());
			transacaoEntity.setCD_RRN(transacao.getCodigoRRN());
			transacaoEntity.setCD_TPO_MSGEM(transacao.getTipoMensagem());

			transacaoEntity.setCD_VDDOR(vendedor.getIdStelo());
			transacaoEntity.setCD_PROCM(transacao.getCodigoProcessamento().intValue());
			transacaoEntity.setVR_TRANS(transacao.getValorTransacao());

			c.setTime(transacao.getDataTransmissao());
			c.set(Calendar.MILLISECOND, 0);
			transacaoEntity.setDT_HORA_TRNSM(c.getTime());


			transacaoEntity.setCD_TRACE(transacao.getCodigoTrace());
			if (transacao.getCodigoAutorizador() != null) {
				transacaoEntity.setCD_AUTRZ(transacao.getCodigoAutorizador().trim());
			}
			transacaoEntity.setNU_TERM(transacao.getNumeroTerminal());
			
			transacaoEntity.setCD_CRDEN(transacao.getCodigoCredenciadora());
			
			transacaoEntity.setCD_TPO_PGTO(pagamento.getCodigoTipoPagamento().intValue());
			transacaoEntity.setCD_PPRIE(pagamento.getCodigoProprietario());
			transacaoEntity.setCD_PCMTO(parcelamento.getCodigoParcelamento().intValue());
			transacaoEntity.setCD_MOEDA(transacao.getCodigoMoeda().intValue());
			transacaoEntity.setCD_RESPT(transacao.getCodigoResposta());
			transacaoEntity.setCD_SUB_MOTVO_REJEI(transacao.getCodigoRejeicao());
			transacaoEntity.setCD_RETOR(transacao.getCodigoRetorno());
			transacaoEntity.setCD_PDIDO(pedido.getCodigoPedido());
			transacaoEntity.setDS_RETOR(transacao.getDescricaoRetorno());
			transacaoEntity.setCD_CANAL_TRANS(pedido.getOrigem());
			transacaoEntity.setID_TRANS_CANAL(pedido.getIdTransacaoCanalM1());

			if (pedido.getVenda() != null){
				transacaoEntity.setCD_PDIDO_EC(pedido.getVenda().getCodigoPedidoEstabelecimentoComercial());
				
				//Se for VTEX na agenda deve aparecer a informacao "paymentId" (pagamento.IdPagamentoEc) em vez do venda.codigoPedidoEstabelecimentoComercial
				//Obs: Certificar que o valor idPagamentoEC nao seja vazio ou 0, pois neste caso o SOA -> OSB nao mandou o valor			
				if (pedido.getVenda().getDescricaoPedidoEstabelecimentoComercial() != null && 
						pedido.getVenda().getDescricaoPedidoEstabelecimentoComercial().toUpperCase().equals("VTEX")
						&& pagamento != null
						&& pagamento.getIdPagamentoEc() != null
						&& pagamento.getIdPagamentoEc().trim().isEmpty()) {
					transacaoEntity.setCD_PDIDO_EC(pagamento.getIdPagamentoEc());
				}
			}

			final Date dataCaptura = transacao.getDataCaptura();
			if (dataCaptura != null) {
				final String sDataCaptura = formatDate(dataCaptura, "MMdd");
				final Integer intDataCaptura = Integer.parseInt(sDataCaptura);
				transacaoEntity.setDT_CAPTU(intDataCaptura);
			}

			if (transacao.getFormaCaptura() != null) {
				transacaoEntity.setCD_FORMA_CAPTU(Integer.parseInt(transacao.getFormaCaptura()));
			}

			String idTransacaoCanalM1 = pedido.getIdTransacaoCanalM1();

			if (idTransacaoCanalM1 != null && !idTransacaoCanalM1.isEmpty()) {
				transacaoEntity.setID_TRANS_CANAL(idTransacaoCanalM1);
			}

			return transacaoEntity;
		}	
	
	 private String formatDate(final Date date, final String format) {
		 
		                  if (date == null) {
		                          return null;
		                  }
		 
		                  final DateFormat df = new SimpleDateFormat(format);
		                  String dateFormat;
		                  dateFormat = df.format(date);
		 
		                  return dateFormat;
		          }

	private Transacao preencheTransacao(PagamentoEntity pagamentoEntity,
			final PedidoEntity pedidoEntity, final VendaEntity vendaEntity) throws Exception {

			final Transacao tran = new Transacao();

				tran.setValorTransacao(pagamentoEntity.getVR_PGTO());
			
			final Long nu_term = buscarTerminal(pedidoEntity);

			if (nu_term != null) {
				tran.setCodigoTerminal(nu_term.toString());
				tran.setNumeroTerminal(nu_term.toString());
			} else {
				tran.setCodigoTerminal(CODIGO_DEFAULT_TERMINAL);
				tran.setNumeroTerminal(NUMERO_DEFAULT_TERMINAL);
			}


			final GregorianCalendar gCal = new GregorianCalendar();
			 
			if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(PedidoOrigemType.M1.getValue())
				&& !TipoPagamentoType.BOLETO.getValue().equals(pagamentoEntity.getCD_TPO_PGTO())) {
				tran.setTipoMensagem(TIPO_MSG_COMPRA);
				gCal.setTime(pagamentoEntity.getDT_PGTO());
			} else {
				if (pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.APROVADA.getValue())) {
					tran.setTipoMensagem(TIPO_MSG_COMPRA);
					gCal.setTime(pagamentoEntity.getDT_PGTO());
				} else if (pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.EMCANCELAMENTO.getValue()) ||
						pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.CANCELADA.getValue()) ||
						pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.EM_CANCELAMENTO_PARCIAL.getValue()) ||
						pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.CANCELADA_PARCIAL.getValue())) {
					
					tran.setTipoMensagem(TIPO_MSG_CANCELAMENTO);
					gCal.setTime(pagamentoEntity.getDT_ULT_CANC());
				}
			}

			tran.setCodigoRRN(pagamentoEntity.getCD_RRN());
			tran.setCodigoProcessamento(0L);
			
			tran.setDataTransacao(gCal.getTime());
			tran.setDataTransmissao(gCal.getTime());
			tran.setCodigoMoeda(BigInteger.valueOf(986L));
			tran.setCanalOrigem(pedidoEntity.getCD_ORIGEM_PRODUTO());
			tran.setCodigoTransacao(0L);
			
			if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(PedidoOrigemType.MPOS.getValue())) {
				tran.setFormaCaptura("0510");
				if (pedidoEntity.getNU_NSU_STELO() != null) {
					tran.setCodigoTrace(Integer.parseInt(pedidoEntity.getNU_NSU_STELO()));
				}

			} else if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(PedidoOrigemType.M1.getValue())
				&& !TipoPagamentoType.BOLETO.getValue().equals(pagamentoEntity.getCD_TPO_PGTO())) {
				tran.setFormaCaptura("0101");
				tran.setCodigoTrace(pagamentoEntity.getCD_TRACE());
			} else {
				tran.setFormaCaptura("0101");
				tran.setCodigoTrace(0);
			}
			
			if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(PedidoOrigemType.MPOS.getValue())
					|| pedidoEntity.getCD_ORIGEM_PRODUTO().equals(PedidoOrigemType.M1.getValue())
					|| pedidoEntity.getCD_ORIGEM_PRODUTO().equals(PedidoOrigemType.SUBADQUIRENTE.getValue())) {
				tran.setCodigoCredenciadora(0);
			} else {
				tran.setCodigoCredenciadora(1);
			}
			
			tran.setCodigoResposta("00");
			if (pagamentoEntity.getCD_RESPT() != null) {
				tran.setCodigoResposta(pagamentoEntity.getCD_RESPT());
			}
			
			tran.setCodigoAutorizador(pagamentoEntity.getCD_AUTRZ());
			return tran;
		}
	
	private Pedido preenchePedido(final VendaEntity vendaEntity,
			final PedidoEntity pedidoEntity) {

			final Venda venda = Venda.builder()
					.codigoPedidoEstabelecimentoComercial(vendaEntity.getCD_PDIDO_ESTBL_COML())
					.build();

			final Pedido ped = Pedido.builder()
			.venda(venda)
			.codigoPedido(pedidoEntity.getCD_PDIDO())
			.origem(pedidoEntity.getCD_ORIGEM_PRODUTO())
			.idTransacaoCanalM1(pedidoEntity.getID_TRANS_CANAL())
			.build();
			return ped;
		}
	
	private Pagamento preenchePagamento(PagamentoEntity pagamentoEntity) {

		final Moeda moeda = Moeda.builder()
		.codigoMoeda(CODIGO_MOEDA)
		.build();
		
		final Pagamento pag = Pagamento.builder()
		.codigoTipoPagamento(pagamentoEntity.getCD_TPO_PGTO().longValue())
		.codigoProprietario(pagamentoEntity.getCD_PPRIE())
		.moeda(moeda)
        .build();
		
		return pag;
	}

	private Parcelamento preencheParcelamento(PagamentoEntity pagamentoEntity) {
		final Parcelamento par = Parcelamento.builder()
		.codigoParcelamento(pagamentoEntity.getCD_PCMTO().longValue())
		.build();
		return par;
	}
	
	private Pessoa preencheVendedor(final Long codigoVendedor) {
		final Pessoa p = Pessoa.builder()
		.idStelo(codigoVendedor).build();
		return p;
	}
	
	public Long buscarTerminal(PedidoEntity pedidoEntity) throws Exception {

		if (pedidoEntity != null) {
			final Long codigoVendedor = pedidoEntity.getCD_VDDOR_ROOT_PDIDO();

			Long idTecnologia=null;
			
			if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(ORIGEM_SUBADQUIRENCIA) || pedidoEntity.getCD_ORIGEM_PRODUTO().equals(ORIGEM_M1)) {
				idTecnologia = ID_TECNOLOGIA_SUBADQUIRENCIA;
			} else if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(ORIGEM_MPOS)) {
				idTecnologia = ID_TECNOLOGIA_MPOS;
			}  else if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(ORIGEM_WALLET)) {
				idTecnologia = ID_TECNOLOGIA_WALLET;
			} else {
				throw new Exception("Erro ao buscar terminal: Nao foi possivel determinar a origem do Pedido!");
			}

			Long nu_term = terminaisConsultados.get(codigoVendedor);
			if (nu_term != null) {
				return nu_term;
			}
			TerminalEntity terminalEntity =	terminalDAO.getTerminal(codigoVendedor, idTecnologia);
			if(terminalEntity!=null) {
			   nu_term=terminalEntity.getNU_TERM();	
			   terminaisConsultados.put(codigoVendedor, nu_term);
			}
			return nu_term;
		}

		throw new Exception("Erro ao buscar terminal: Nao foi possivel determinar a origem do Pedido!");
	}
		
//-------------------------
	
	private void geraInconsistencia(final PagamentoMatera pagamentoMatera, final PagamentoEntity pagamentoEntity,
			final PedidoEntity pedidoEntity, final VendaEntity vendaEntity,
			final SubMotivoFonteRejeicao subMotivoFonteRejeicao, final MensagemDomain descricaoRejeicao) {

		final InconsistenciaBoletoEntity inconsistencia = new InconsistenciaBoletoEntity();

		if (pedidoEntity != null) {
			inconsistencia.setCD_ORIGE(pedidoEntity.getCD_ORIGEM_PRODUTO());
			inconsistencia.setEMAIL_COMPR(pedidoEntity.getDS_EMAIL_COMPR());

		}
		if (vendaEntity != null) {
			inconsistencia.setNU_PDIDO_EC(vendaEntity.getCD_PDIDO_ESTBL_COML());

		}
		if (pagamentoEntity != null) {
			inconsistencia.setVR_PDIDO(new BigDecimal(pagamentoEntity.getVR_PGTO()));
		}

		if (subMotivoFonteRejeicao != null) {
			inconsistencia.setCD_FONTE_REJEI(subMotivoFonteRejeicao.getIdFonteRejeicao());
			inconsistencia.setCD_MTV_REJEI(subMotivoFonteRejeicao.getMtvCodFonteRejeicao());
			inconsistencia.setCD_SUB_MTV_REJEI(subMotivoFonteRejeicao.getSubMtvCodFonteRejeicao());
		}

		if (descricaoRejeicao != null) {
			inconsistencia.setDS_INCOT(descricaoRejeicao.getMensagem());
		}

		inconsistencia.setCD_VDDOR(pagamentoMatera.getIdStelo());
		inconsistencia.setNU_PDIDO(pagamentoMatera.getCodigoPedido());
		inconsistencia.setCPF(Long.valueOf(pagamentoMatera.getNumeroDocumento()));
		inconsistencia.setDT_PGTO(pagamentoMatera.getPagamento());
		inconsistencia.setDT_VCTO(pagamentoMatera.getVencimento());
		inconsistencia.setVR_BLETO_PG(new BigDecimal(pagamentoMatera.getValor()));

		log.info(inconsistencia.toString());
		inconsistenciaBoletoDAO.inserir(inconsistencia);
	}

	private boolean isPagamentoRealizadoAposVencimento(final PagamentoMatera pagamentoMatera,
			final PagamentoEntity pagamentoEntity) {
		log.info("Verificando se a data do Pagamento é posterior a data de Vencimento");

		Date dataVencimento = pagamentoEntity.getDT_VCTO();
				
		final Date dataPagamento = pagamentoMatera.getPagamento();

		log.debug("### dataVencimento: "+ dataVencimento + " , dataPagamento: "+dataPagamento);

		final Calendar diaSemanaDataVencimento = Calendar.getInstance();

		diaSemanaDataVencimento.setTime(dataVencimento);

		final int diaSemDtVenc = diaSemanaDataVencimento.get(Calendar.DAY_OF_WEEK);

		if (diaSemDtVenc == Calendar.SATURDAY) {
			log.info("A Data de Vencimento é Sábado. Data de Vencimento passa a ser Segunda-feira.");

			diaSemanaDataVencimento.add(Calendar.DAY_OF_MONTH, 2);
			configuraHorarioVencimento(diaSemanaDataVencimento);

			dataVencimento = diaSemanaDataVencimento.getTime();
		} else if (diaSemDtVenc == Calendar.SUNDAY) {
			log.info("A Data de Vencimento é Domingo. Data de Vencimento passa a ser Segunda-feira.");

			diaSemanaDataVencimento.add(Calendar.DAY_OF_MONTH, 1);
			configuraHorarioVencimento(diaSemanaDataVencimento);

			dataVencimento = diaSemanaDataVencimento.getTime();
		}

		if (dataPagamento.after(dataVencimento)) {
			log.info("A Data de Pagamento é Posterior a Data de Vencimento.");

			return true;
		} else {
			log.info("A Data de Pagamento é Anterior a Data de Vencimento.");

			return false;
		}
	}

	private void configuraHorarioVencimento(final Calendar diaSemanaDataVencimento) {
		diaSemanaDataVencimento.set(Calendar.HOUR_OF_DAY, 23);
		diaSemanaDataVencimento.set(Calendar.MINUTE, 59);
		diaSemanaDataVencimento.set(Calendar.SECOND, 59);
		diaSemanaDataVencimento.set(Calendar.MILLISECOND, 999);
	}

	private void cancelaPagamento(final PagamentoMatera pagamentoMatera, final PagamentoEntity pagamentoEntity,
			final PedidoEntity pedidoEntity, final SubMotivoFonteRejeicao subMotivoFonteRejeicao) {
		log.info("Inicio cancelaPagamento " + pagamentoMatera.getCodigoPedido());

		if (pagamentoEntity != null && pedidoEntity != null) {
			pagamentoEntity.setCD_STTUS_PGTO(PagamentoStatusType.CANCELADA.getValue());
			pagamentoEntity.setDT_ULT_CANC(new Date());
			pagamentoEntity.setVR_CANCT(pagamentoMatera.getValor());
			pagamentoEntity.setCD_FNT_REJEI(subMotivoFonteRejeicao.getIdFonteRejeicao());
			pagamentoEntity.setCD_MOTVO_REJEI(subMotivoFonteRejeicao.getMtvCodFonteRejeicao());
			pagamentoEntity.setCD_SUBMOTVO_REJEI(subMotivoFonteRejeicao.getSubMtvCodFonteRejeicao());
			pagamentoEntity.setCD_STTUS_PGTO(PagamentoStatusType.CANCELADA.getValue());

		}

		log.info("Fim cancelaPagamento " + pagamentoMatera.getCodigoPedido());
	}

}
