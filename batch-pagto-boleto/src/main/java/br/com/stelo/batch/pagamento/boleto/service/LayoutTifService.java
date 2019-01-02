package br.com.stelo.batch.pagamento.boleto.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stelo.batch.helper.FixedLengthField;
import br.com.stelo.batch.helper.FixedLengthField.Behaviour;
import br.com.stelo.batch.helper.PagamentoStatusType;
import br.com.stelo.batch.helper.StringHandler;
import br.com.stelo.batch.pagamento.boleto.model.RegistroArquivoTiff;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PagamentoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;

@Service
public class LayoutTifService implements ILayoutTifService {

	private static final Logger log = LoggerFactory.getLogger(LayoutTifService.class);

	private static final int TIPO_MSG_COMPRA = 220;
	private static final int TIPO_MSG_CANCELAMENTO = 420;
	private static final long NUM_ORIGINAL = 0L;
	private static final String FORMATO_DATA = "yyyyMMdd";
	private static final String FORMATO_HORA = "HHmmss";
	private static final int CD_PROCESSAMENTO = 0;
	private static final Long CD_DEFAULT_TERMINAL = 0L;
	private static final String PREFIX_CD_CLIENTE = "999901";
	private static final String DATA_EXPIRACAO = "000000";
	private static final String CD_MOEDA = "986";
	private static final String CD_ENTRADA_BOLETO = "0101";
	private static final int CD_PAIS_BRASIL = 76;
	private static final int TRACE_NUMBER_BOLETO = 0;
	private static final Long ID_TECNOLOGIA_SUB = 11L;
	private static final long CD_DEPARTAMENTO = 1L;
	private static final String TIPO_TECNOLOGIA = "";
	private static final String POS_DATA = "";
	private static final String METODO_VERIFICACAO_BOLETO = "S";
	private static final String NUM_CARTAO = "000";
	private static final String SOURCE_INTERFACE_BOLETO = "M";
	private static final Integer PRODUCT_HP_CREDITO = 1;
	private static final Integer PRODUCT_HP_DEBITO = 2;
	private static final int TAMANHO_NOME_EC = 25;
	private static final String CD_RESPOSTA_BOLETO = "00";
	private static final String CD_TRANSACAO = "";
	private static final long TAXA_CAMBIO = 0L;
	private static final long CD_TRANSACAO_VISA = 0L;
	private static final String DADOS_REDE = "";
	private static final String DATA_LIQUIDACAO = "0000";
	private static final String CD_SEGURANCA = "";
	private static final int MCC = 0;
	private static final long CD_PROMOCAO = 0L;
	private static final double VLR_ENTRADA = 0.0;
	private static final double TAXA_EMBARQUE = 0.0;
	private static final double VLR_REEMBOLSO = 0.0;
	private static final long CD_REQUIRENTE = 15L;
	private static final String DATA_ORIGINAL_TRANSACAO = "00000000";
	private static final String HORA_ORIGINAL_TRANSACAO = "000000";
	private static final String RRN_ORIGINAL = "";
	private static final String CD_ORIGINAL_TRANSACAO = "";
	private static final String CODIGO_SERVICO = "";
	private static final int FASE0_COBRANDING_MERCHANT_FLAG = 0;
	private static final long FASE0_COBRANDING_BINGROUP = 0L;
	private static final int CMS_PRODUCT = 26;
	private static final int CMS_TRANSACTION_TYPE = 0;
	private static final long CMS_BIN_GROUP = 0L;
	private static final String TIPO_CONTA = "";
	private static final Long DCC_INDICATOR = 0L;
	private static final Long RECONCILIATION_AMOUNT = 0L;
	private static final Long RECONCILIATION_CURRENCY = 0L;
	private static final Long AMOUNT_ADDITIONALS = 0L;
	private static final Long AMOUNT_ADDITIONALS_CURRENCY = 0L;
	private static final Long DCC_EXCHANGE_FOR_RECONCILIATION = 0L;
	private static final Long DCC_EXCHANGE_RATE_WITH_MARKUP = 0L;
	private static final Long DCC_EXCHANGE_RATE_WITHOUT_MARKUP = 0L;

	@Autowired
	private ITerminalService terminalService;

	@Override
	public RegistroArquivoTiff getRegistroArquivoTiff(PagamentoEntity pagamentoEntity, PedidoEntity pedidoEntity,
			Integer numSequencia, String nomeFantasia) {

		final int mti = configuraMTI(pagamentoEntity);

		RegistroArquivoTiff arquivoTiff = new RegistroArquivoTiff();
		setColunas003a050(pagamentoEntity, numSequencia, arquivoTiff, mti);
		setColunas051a102(pagamentoEntity, pedidoEntity, arquivoTiff);
		setColunas103a150(pagamentoEntity, arquivoTiff, pedidoEntity);
		setColunas151a694(pagamentoEntity, arquivoTiff, pedidoEntity);
		setColunas695a860(pagamentoEntity, nomeFantasia, arquivoTiff);
		setColunas861a902(pagamentoEntity, arquivoTiff, mti);
		setColunas903a1299(pagamentoEntity, arquivoTiff);

		return arquivoTiff;
	}

	private int configuraMTI(PagamentoEntity pagamentoEntity) {

		if (pagamentoEntity.getCD_STTUS_PGTO().equals(PagamentoStatusType.APROVADA.getValue())) {
			return TIPO_MSG_COMPRA;
		}
		return TIPO_MSG_CANCELAMENTO;
	}

	private void setColunas003a050(PagamentoEntity pagamentoEntity, Integer numSequencia,
			RegistroArquivoTiff arquivoTiff, int mti) {

		// 003 a 011
		arquivoTiff.setSequencial(FixedLengthField.to(9, Behaviour.LEFT_ZERO).setInt(numSequencia).toString());
		// 012 a 020
		arquivoTiff.setNumOriginal(FixedLengthField.to(9, Behaviour.LEFT_ZERO).setLong(NUM_ORIGINAL).toString());
		// 021 a 028
		// 045 a 050
		if (TIPO_MSG_COMPRA == mti) {
			final Date dataPagamento = pagamentoEntity.getDT_PGTO();
			arquivoTiff.setDataTranscao(
					FixedLengthField.to(8, Behaviour.LEFT_ZERO).setDate(dataPagamento, FORMATO_DATA).toString());
			arquivoTiff.setHoraTransacao(
					FixedLengthField.to(6, Behaviour.LEFT_ZERO).setDate(dataPagamento, FORMATO_HORA).toString());
		} else if (TIPO_MSG_CANCELAMENTO == mti) {

			final Date dtUltimoCancelamento = pagamentoEntity.getDT_ULT_CANC();
			arquivoTiff.setDataTranscao(
					FixedLengthField.to(8, Behaviour.LEFT_ZERO).setDate(dtUltimoCancelamento, FORMATO_DATA).toString());
			arquivoTiff.setHoraTransacao(
					FixedLengthField.to(6, Behaviour.LEFT_ZERO).setDate(dtUltimoCancelamento, FORMATO_HORA).toString());
		}

		// 029 a 032
		arquivoTiff.setMti(FixedLengthField.to(4, Behaviour.LEFT_ZERO).setInt(mti).toString());
		// 033 a 038
		arquivoTiff.setCodigoProcessamento(
				FixedLengthField.to(6, Behaviour.LEFT_ZERO).setInt(CD_PROCESSAMENTO).toString());
		// 039 a 44
		arquivoTiff.setCodigoAutorizacao(
				FixedLengthField.to(6, Behaviour.LEFT_ZERO).setString(pagamentoEntity.getCD_AUTRZ()).toString());
	}

	private void setColunas051a102(PagamentoEntity pagamentoEntity, PedidoEntity pedidoEntity,
			RegistroArquivoTiff arquivoTiff) {

		// 051 a 058
		arquivoTiff.setNumeroTerminal(
				FixedLengthField.to(8, Behaviour.LEFT_ZERO).setString(getNumeroTerminal(pedidoEntity)).toString());
		// 059 a 077
		arquivoTiff.setIdCliente(FixedLengthField.to(19, Behaviour.RIGHT_ZERO)
				.setString(PREFIX_CD_CLIENTE + pagamentoEntity.getCD_VDDOR()).toString());
		// 078 a 083
		arquivoTiff.setDataExpiracao(FixedLengthField.to(6, Behaviour.LEFT_ZERO).setString(DATA_EXPIRACAO).toString());
		// 084 a 086
		arquivoTiff.setCodigoMoeda(FixedLengthField.to(3, Behaviour.LEFT_ZERO).setString(CD_MOEDA).toString());
		// 087 a 102
		arquivoTiff.setValorTransacao(
				FixedLengthField.to(16, Behaviour.LEFT_ZERO).setDouble(pagamentoEntity.getVR_PGTO(), 4).toString());
	}

	private void setColunas103a150(PagamentoEntity pagamentoEntity, RegistroArquivoTiff arquivoTiff,
			PedidoEntity pedidoEntity) {

		// 103 a 106
		arquivoTiff.setEntrada(FixedLengthField.to(4, Behaviour.LEFT_ZERO).setString(CD_ENTRADA_BOLETO).toString());
		// 107 a 109
		arquivoTiff.setCodigoPais(FixedLengthField.to(3, Behaviour.LEFT_ZERO).setInt(CD_PAIS_BRASIL).toString());
		// 110 a 115
		arquivoTiff.setTraceNumber(FixedLengthField.to(6, Behaviour.LEFT_ZERO).setInt(TRACE_NUMBER_BOLETO).toString());
		// 116 a 127
		arquivoTiff
				.setRrn(FixedLengthField.to(12, Behaviour.LEFT_ZERO).setString(pagamentoEntity.getCD_RRN()).toString());
		// 128 a 131
		arquivoTiff.setCodigoPlanoVenda(
				FixedLengthField.to(4, Behaviour.LEFT_ZERO).setInt(pagamentoEntity.getCD_PCMTO()).toString());
		// 132 a 140
		setIdBranch(pedidoEntity, pagamentoEntity, arquivoTiff);
		// 141 a 149
		arquivoTiff.setIdDepartamento(FixedLengthField.to(9, Behaviour.LEFT_ZERO).setLong(CD_DEPARTAMENTO).toString());
		// 150
		arquivoTiff
				.setTipoTecnologia(FixedLengthField.to(1, Behaviour.LEFT_BLANK).setString(TIPO_TECNOLOGIA).toString());
	}

	private String getNumeroTerminal(PedidoEntity pedidoEntity) {

		Long nu_term = null;
		try {
			nu_term = terminalService.buscarTerminal(pedidoEntity);
		} catch (Exception e) {
			log.error("Erro ao consultar terminal do EC", e);
		}

		if (nu_term == null) {
			nu_term = CD_DEFAULT_TERMINAL;
		}
		return nu_term.toString();
	}

	private void setIdBranch(PedidoEntity pedidoEntity, PagamentoEntity pagamentoEntity,
			RegistroArquivoTiff arquivoTiff) {

		final Long codigoVendedor = pagamentoEntity.getCD_VDDOR();
		String branchId = codigoVendedor.toString();

		arquivoTiff.setIdBranch(FixedLengthField.to(9, Behaviour.LEFT_ZERO)
				.setString(branchId.concat(ID_TECNOLOGIA_SUB.toString())).toString());

	}

	private void setColunas151a694(PagamentoEntity pagamentoEntity, RegistroArquivoTiff arquivoTiff,
			PedidoEntity pedidoDomain) {

		// 151 a 176
		arquivoTiff.setPosData(FixedLengthField.to(26, Behaviour.LEFT_BLANK).setString(POS_DATA).toString());
		// 177
		arquivoTiff.setMetodoVerificacao(
				FixedLengthField.to(1, Behaviour.LEFT_BLANK).setString(METODO_VERIFICACAO_BOLETO).toString());
		// 178 a 180
		arquivoTiff.setNumCartao(FixedLengthField.to(3, Behaviour.LEFT_ZERO).setString(NUM_CARTAO).toString());
		// 181
		arquivoTiff.setSourceInterface(
				FixedLengthField.to(1, Behaviour.LEFT_BLANK).setString(SOURCE_INTERFACE_BOLETO).toString());
		// 182 a 184
		arquivoTiff.setFieldLength(FixedLengthField.to(3, Behaviour.LEFT_ZERO).setString("000").toString());
		// 185 a 694
		arquivoTiff.setField(FixedLengthField.to(510, Behaviour.LEFT_BLANK).setString("").toString());
	}

	private void setColunas695a860(PagamentoEntity pagamentoEntity, String nomeFantasia,
			RegistroArquivoTiff arquivoTiff) {
		// 695 a 696
		arquivoTiff.setCodigoResposta(
				FixedLengthField.to(2, Behaviour.LEFT_BLANK).setString(CD_RESPOSTA_BOLETO).toString());
		// 697 a 711
		arquivoTiff
				.setIdTransacaoVisa(FixedLengthField.to(15, Behaviour.LEFT_ZERO).setLong(CD_TRANSACAO_VISA).toString());
		// 712 a 720
		arquivoTiff.setDadosRede(FixedLengthField.to(9, Behaviour.LEFT_BLANK).setString(DADOS_REDE).toString());
		// 721 a 724
		arquivoTiff
				.setDataLiquidacao(FixedLengthField.to(4, Behaviour.LEFT_BLANK).setString(DATA_LIQUIDACAO).toString());
		// 725 a 727
		arquivoTiff.setIdSeguranca(FixedLengthField.to(3, Behaviour.LEFT_BLANK).setString(CD_SEGURANCA).toString());
		// 728 a 736
		arquivoTiff.setCodigoIdentificacao(
				FixedLengthField.to(9, Behaviour.LEFT_ZERO).setLong(pagamentoEntity.getCD_VDDOR()).toString());
		// 737 a 740
		arquivoTiff.setMcc(FixedLengthField.to(4, Behaviour.LEFT_ZERO).setInt(MCC).toString());
		// 741 a 749
		arquivoTiff.setIdPromocao(FixedLengthField.to(9, Behaviour.LEFT_ZERO).setLong(CD_PROMOCAO).toString());
		// 750 a 765
		arquivoTiff.setValorEntrada(FixedLengthField.to(16, Behaviour.LEFT_ZERO).setDouble(VLR_ENTRADA, 2).toString());
		// 766 a 781
		arquivoTiff
				.setTaxaEmbarque(FixedLengthField.to(16, Behaviour.LEFT_ZERO).setDouble(TAXA_EMBARQUE, 2).toString());
		// 782 a 797
		arquivoTiff
				.setValorReembolso(FixedLengthField.to(16, Behaviour.LEFT_ZERO).setDouble(VLR_REEMBOLSO, 2).toString());
		// 798 a 808
		arquivoTiff.setIdRequirente(FixedLengthField.to(11, Behaviour.LEFT_ZERO).setLong(CD_REQUIRENTE).toString());
		// 809
		setProductType(arquivoTiff, pagamentoEntity);
		// 810 a 821
		arquivoTiff.setIdTransacao(FixedLengthField.to(12, Behaviour.LEFT_BLANK).setString(CD_TRANSACAO).toString());
		// 822 a 835
		arquivoTiff.setTaxaCambio(FixedLengthField.to(14, Behaviour.LEFT_ZERO).setLong(TAXA_CAMBIO).toString());
		// 836 a 860
		setNomeEC(nomeFantasia, arquivoTiff);
	}

	protected void setProductType(RegistroArquivoTiff arquivoTiff, PagamentoEntity pagamentoEntity) {

		if (PRODUCT_HP_CREDITO.equals(pagamentoEntity.getCD_TPO_PGTO())) {
			arquivoTiff.setIdBin(FixedLengthField.to(1, Behaviour.LEFT_ZERO).setInt(PRODUCT_HP_CREDITO).toString());
		} else {
			arquivoTiff.setIdBin(FixedLengthField.to(1, Behaviour.LEFT_ZERO).setInt(PRODUCT_HP_DEBITO).toString());
		}
	}

	private void setNomeEC(String nomeFantasia, RegistroArquivoTiff arquivoTiff) {
		String nomeFantasiaTratado = nomeFantasia;

		if (StringUtils.isNotBlank(nomeFantasia) && nomeFantasia.length() > TAMANHO_NOME_EC) {
			nomeFantasiaTratado = StringHandler.substringSafe(nomeFantasia, TAMANHO_NOME_EC);
		}
		arquivoTiff.setNomeEC(
				FixedLengthField.to(TAMANHO_NOME_EC, Behaviour.RIGHT_BLANK).setString(nomeFantasiaTratado).toString());
	}

	private void setColunas861a902(PagamentoEntity pagamentoEntity, RegistroArquivoTiff arquivoTiff, int mti) {
		// 861 a 868
		// 869 a 874
		// 887 a 898
		if (TIPO_MSG_COMPRA == mti) {
			arquivoTiff.setDataOriginalTransacao(
					FixedLengthField.to(8, Behaviour.LEFT_ZERO).setString(DATA_ORIGINAL_TRANSACAO).toString());
			arquivoTiff.setHoraOriginalTransacao(
					FixedLengthField.to(6, Behaviour.LEFT_ZERO).setString(HORA_ORIGINAL_TRANSACAO).toString());
			arquivoTiff
					.setRrnOriginal(FixedLengthField.to(12, Behaviour.LEFT_BLANK).setString(RRN_ORIGINAL).toString());
		} else if (TIPO_MSG_CANCELAMENTO == mti) {
			arquivoTiff.setDataOriginalTransacao(FixedLengthField.to(8, Behaviour.LEFT_ZERO)
					.setDate(pagamentoEntity.getDT_PGTO(), FORMATO_DATA).toString());
			arquivoTiff.setHoraOriginalTransacao(FixedLengthField.to(6, Behaviour.LEFT_ZERO)
					.setDate(pagamentoEntity.getDT_PGTO(), FORMATO_HORA).toString());
			arquivoTiff.setRrnOriginal(
					FixedLengthField.to(12, Behaviour.LEFT_BLANK).setString(pagamentoEntity.getCD_RRN()).toString());
		}
		// 875 a 886
		arquivoTiff.setIdOriginalTransacao(
				FixedLengthField.to(12, Behaviour.LEFT_BLANK).setString(CD_ORIGINAL_TRANSACAO).toString());
		// 899 a 901
		arquivoTiff.setCodigoServico(FixedLengthField.to(3, Behaviour.LEFT_BLANK).setString(CODIGO_SERVICO).toString());
		// 902
		arquivoTiff.setMerchante(
				FixedLengthField.to(1, Behaviour.LEFT_ZERO).setInt(FASE0_COBRANDING_MERCHANT_FLAG).toString());
	}

	private void setColunas903a1299(PagamentoEntity pagamentoEntity, RegistroArquivoTiff arquivoTiff) {

		// 903 a 911
		arquivoTiff.setCoBinGroup(
				FixedLengthField.to(9, Behaviour.LEFT_ZERO).setLong(FASE0_COBRANDING_BINGROUP).toString());
		// 912 a 913
		arquivoTiff.setCmsProduct(FixedLengthField.to(2, Behaviour.LEFT_ZERO).setInt(CMS_PRODUCT).toString());
		// 914 a 916
		arquivoTiff.setTransactionType(
				FixedLengthField.to(3, Behaviour.LEFT_ZERO).setInt(CMS_TRANSACTION_TYPE).toString());
		// 917 a 925
		arquivoTiff.setBinGroup(FixedLengthField.to(9, Behaviour.LEFT_ZERO).setLong(CMS_BIN_GROUP).toString());
		// 926 a 929
		arquivoTiff.setTipoConta(FixedLengthField.to(4, Behaviour.LEFT_BLANK).setString(TIPO_CONTA).toString());
		// 930
		arquivoTiff.setDCCIndicator(FixedLengthField.to(1, Behaviour.LEFT_ZERO).setLong(DCC_INDICATOR).toString());
		// 941 a 944
		arquivoTiff.setReconciliationAmount(
				FixedLengthField.to(14, Behaviour.LEFT_ZERO).setLong(RECONCILIATION_AMOUNT).toString());
		// 945 a 947
		arquivoTiff.setReconciliationCurrency(
				FixedLengthField.to(3, Behaviour.LEFT_ZERO).setLong(RECONCILIATION_CURRENCY).toString());
		// 948 a 961
		arquivoTiff.setAmountAdditionals(
				FixedLengthField.to(14, Behaviour.LEFT_ZERO).setLong(AMOUNT_ADDITIONALS).toString());
		// 962 a 964
		arquivoTiff.setAmountAdditionalsCurrency(
				FixedLengthField.to(3, Behaviour.LEFT_ZERO).setLong(AMOUNT_ADDITIONALS_CURRENCY).toString());
		// 965 a 978
		arquivoTiff.setDCCExchangeForReconciliation(
				FixedLengthField.to(14, Behaviour.LEFT_ZERO).setLong(DCC_EXCHANGE_FOR_RECONCILIATION).toString());
		// 979 a 992
		arquivoTiff.setDCCExchangeRateWithMarkup(
				FixedLengthField.to(14, Behaviour.LEFT_ZERO).setLong(DCC_EXCHANGE_RATE_WITH_MARKUP).toString());
		// 993 a 1006
		arquivoTiff.setDCCExchangeRateWithoutMarkup(
				FixedLengthField.to(14, Behaviour.LEFT_ZERO).setLong(DCC_EXCHANGE_RATE_WITHOUT_MARKUP).toString());
		// 1007 a 1233
		arquivoTiff.setCommercialFinancialData("");
		// 1234 a 1238
		arquivoTiff.setAgentId("");
		// 1239 a 1253
		arquivoTiff.setAirlineTicket("");
		// 1254 a 1267
		arquivoTiff.setOriginalTax("");
		// 1268 a 1283
		arquivoTiff.setTotalAmountOriginal("");
		// 1284 a 1300
		arquivoTiff.setFiller("");
	}

}
