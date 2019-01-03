package br.com.stelo.batch.pagamento.boleto.service;

//import java.math.BigInteger;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
//import java.util.GregorianCalendar;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.stelo.batch.helper.PagamentoStatusType;
//import br.com.stelo.batch.pagamento.boleto.model.Moeda;
//import br.com.stelo.batch.pagamento.boleto.model.Pagamento;
import br.com.stelo.batch.pagamento.boleto.model.PagamentoMatera;
//import br.com.stelo.batch.pagamento.boleto.model.Parcelamento;
//import br.com.stelo.batch.pagamento.boleto.model.Pedido;
//import br.com.stelo.batch.pagamento.boleto.model.PedidoOrigemType;
//import br.com.stelo.batch.pagamento.boleto.model.Pessoa;
//import br.com.stelo.batch.pagamento.boleto.model.TipoPagamentoType;
//import br.com.stelo.batch.pagamento.boleto.model.Transacao;
//import br.com.stelo.batch.pagamento.boleto.model.Venda;
//import br.com.stelo.batch.pagamento.boleto.repository.dao.ITransacaoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PagamentoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;
//import br.com.stelo.batch.pagamento.boleto.repository.entity.TransacaoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.VendaEntity;

@Service
public class TransacaoService implements ITransacaoService {

	private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);

	//private static final String CODIGO_DEFAULT_TERMINAL = "0";
	//private static final String NUMERO_DEFAULT_TERMINAL = "0";
	//private static final int TIPO_MSG_COMPRA = 220;
	//private static final int TIPO_MSG_CANCELAMENTO = 420;
	//private static final String CODIGO_MOEDA = "986";

	//@Autowired
	//private ITransacaoDAO transacaoDAO;

	//@Autowired
	//private ITerminalService terminalService;

	@Override
	public void processaPagamentoAprovado(final PagamentoMatera pagamentoMatera, final PagamentoEntity pagamentoEntity,
			final VendaEntity vendaEntity, final PedidoEntity pedidoEntity) throws Exception {
		pagamentoEntity.setCD_STTUS_PGTO(PagamentoStatusType.APROVADA.getValue());
		pagamentoEntity.setDT_PGTO(new Date());
		pagamentoEntity.setVR_PG(pagamentoMatera.getValor());
		pedidoEntity.setCD_STTUS_PDIDO(PagamentoStatusType.APROVADA.getValue());

		//insereHistoricoTransacao(pagamentoEntity, vendaEntity, pedidoEntity);

		log.info("Registro processado com sucesso! " + pagamentoMatera);
		log.info("Pagamento OK - Marcado para incluir transação e gerar TIFF nos steps seguintes...");
	}
/*
	private void insereHistoricoTransacao(PagamentoEntity pagamentoEntity, VendaEntity vendaEntity,
			PedidoEntity pedidoEntity) throws Exception {

		try {

			final Transacao paramTransacao = preencheTransacao(pagamentoEntity, pedidoEntity, vendaEntity);
			final Pedido paramPedido = preenchePedido(vendaEntity, pedidoEntity);
			final Pagamento paramPagamento = preenchePagamento(pagamentoEntity);
			final Parcelamento paramParcelamento = preencheParcelamento(pagamentoEntity);
			final Pessoa paramPessoa = preencheVendedor(vendaEntity.getCD_VDDOR());

			TransacaoEntity transacaoEntity = buildTrancacaoEntity(paramTransacao, paramPedido, paramPagamento,
					paramParcelamento, paramPessoa);
			transacaoDAO.inserir(transacaoEntity);

		} catch (final Exception e) {
			log.error("Erro ao inserir historico de transacao:", e);
			throw new Exception("ERRPDD0073", e);
		}
	}

	private TransacaoEntity buildTrancacaoEntity(final Transacao transacao, final Pedido pedido,
			final Pagamento pagamento, final Parcelamento parcelamento, final Pessoa vendedor) {

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

		if (pedido.getVenda() != null) {
			transacaoEntity.setCD_PDIDO_EC(pedido.getVenda().getCodigoPedidoEstabelecimentoComercial());

			if (pedido.getVenda().getDescricaoPedidoEstabelecimentoComercial() != null
					&& pedido.getVenda().getDescricaoPedidoEstabelecimentoComercial().toUpperCase().equals("VTEX")
					&& pagamento != null && pagamento.getIdPagamentoEc() != null
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

	private Transacao preencheTransacao(PagamentoEntity pagamentoEntity, final PedidoEntity pedidoEntity,
			final VendaEntity vendaEntity) throws Exception {

		final Transacao tran = new Transacao();

		tran.setValorTransacao(pagamentoEntity.getVR_PGTO());

		final Long nu_term = terminalService.buscarTerminal(pedidoEntity);

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
			} else if (pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.EMCANCELAMENTO.getValue())
					|| pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.CANCELADA.getValue())
					|| pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.EM_CANCELAMENTO_PARCIAL.getValue())
					|| pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.CANCELADA_PARCIAL.getValue())) {

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

	private Pedido preenchePedido(final VendaEntity vendaEntity, final PedidoEntity pedidoEntity) {

		final Venda venda = Venda.builder().codigoPedidoEstabelecimentoComercial(vendaEntity.getCD_PDIDO_ESTBL_COML())
				.build();

		final Pedido ped = Pedido.builder().venda(venda).codigoPedido(pedidoEntity.getCD_PDIDO())
				.origem(pedidoEntity.getCD_ORIGEM_PRODUTO()).idTransacaoCanalM1(pedidoEntity.getID_TRANS_CANAL())
				.build();
		return ped;
	}

	private Pagamento preenchePagamento(PagamentoEntity pagamentoEntity) {

		final Moeda moeda = Moeda.builder().codigoMoeda(CODIGO_MOEDA).build();

		final Pagamento pag = Pagamento.builder().codigoTipoPagamento(pagamentoEntity.getCD_TPO_PGTO().longValue())
				.codigoProprietario(pagamentoEntity.getCD_PPRIE()).moeda(moeda).build();

		return pag;
	}

	private Parcelamento preencheParcelamento(PagamentoEntity pagamentoEntity) {
		final Parcelamento par = Parcelamento.builder().codigoParcelamento(pagamentoEntity.getCD_PCMTO().longValue())
				.build();
		return par;
	}

	private Pessoa preencheVendedor(final Long codigoVendedor) {
		final Pessoa p = Pessoa.builder().idStelo(codigoVendedor).build();
		return p;
	}
*/
}
