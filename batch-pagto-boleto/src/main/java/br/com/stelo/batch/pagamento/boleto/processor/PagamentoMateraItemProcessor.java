package br.com.stelo.batch.pagamento.boleto.processor;

//import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//import br.com.stelo.batch.helper.MensagemDomain;
import br.com.stelo.batch.helper.PagamentoStatusType;
import br.com.stelo.batch.helper.SubMotivoFonteRejeicao;
import br.com.stelo.batch.pagamento.boleto.model.PagamentoMatera;
import br.com.stelo.batch.pagamento.boleto.model.RegistroArquivoTiff;
import br.com.stelo.batch.pagamento.boleto.model.TipoRegistroMatera;
//import br.com.stelo.batch.pagamento.boleto.repository.dao.IInconsistenciaBoletoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.dao.IPagamentoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.dao.IPedidoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.dao.IVendaDAO;
//import br.com.stelo.batch.pagamento.boleto.repository.entity.InconsistenciaBoletoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PagamentoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.VendaEntity;
import br.com.stelo.batch.pagamento.boleto.service.ILayoutTifService;
import br.com.stelo.batch.pagamento.boleto.service.ITransacaoService;

public class PagamentoMateraItemProcessor implements ItemProcessor<PagamentoMatera, RegistroArquivoTiff> {

	private static final Logger log = LoggerFactory.getLogger(PagamentoMateraItemProcessor.class);

	//private static final MensagemDomain PEDIDO_PAGO = new MensagemDomain("ERRBT0007");
	//private static final MensagemDomain NAO_ENCONTRADO = new MensagemDomain("ERRBT0002");
	//private static final MensagemDomain PGTO_CANCELADO = new MensagemDomain("ERRBT0003");
	//private static final MensagemDomain PGTO_MENOR = new MensagemDomain("ERRBT0005");
	//private static final MensagemDomain PGTO_VENCIDO = new MensagemDomain("ERRBT0006");
	//private static final MensagemDomain ERRO_SISTEMICO = new MensagemDomain("ERRBT0001");

    @Value("#{StepExecution}")
    private StepExecution stepExecution;
	
	@Autowired
	private IPagamentoDAO pagamentoDAO;

	@Autowired
	private IPedidoDAO pedidoDAO;

	@Autowired
	private IVendaDAO vendaDAO;

	@Autowired
    private ITransacaoService transacaoService;
	
	//@Autowired
	//private IInconsistenciaBoletoDAO inconsistenciaBoletoDAO;

	@Autowired
	private ILayoutTifService layoutTifService;
	
	@Override
	public RegistroArquivoTiff process(final PagamentoMatera pagamentoMatera) throws Exception {
		RegistroArquivoTiff registroArquivoTiff = null;
		
		final String nomeFantasia = "STELO E";
		
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
					log.error("Pedido [{}] nao encontrado, Gerando inconsistencia...",
							pagamentoMatera.getCodigoPedido());
					//geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
					//		SubMotivoFonteRejeicao.PAGAMENTO_NAO_ENCONTRADO, NAO_ENCONTRADO);
				} else {

					final int COMPARAR_VALOR = Double.compare(pagamentoMatera.getValor(), pagamentoEntity.getVR_PGTO());

					if (PagamentoStatusType.CANCELADA.getValue().equals(pagamentoEntity.getCD_STTUS_PGTO())
							|| PagamentoStatusType.EMCANCELAMENTO.getValue()
									.equals(pagamentoEntity.getCD_STTUS_PGTO())) {
						log.info("Pagamento Cancelado ou Em Cancelamento, Gerando inconsistencia...");
						//geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
						//		SubMotivoFonteRejeicao.PAGAMENTO_DE_PEDIDO_CANCELADO, PGTO_CANCELADO);
					} else if (PagamentoStatusType.APROVADA.getValue().equals(pagamentoEntity.getCD_STTUS_PGTO())) {
						log.info("Pagamento Duplicado, Gerando inconsistencia...");
						//geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
						//		SubMotivoFonteRejeicao.PAGAMENTO_DUPLICADO, PEDIDO_PAGO);
					} else if (COMPARAR_VALOR < 0) {
						log.info("Pagamento com valor menor, Gerando inconsistencia...");
						//geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
						//		SubMotivoFonteRejeicao.A_MENOR, PGTO_MENOR);
						cancelaPagamento(pagamentoMatera, pagamentoEntity, pedidoEntity,
								SubMotivoFonteRejeicao.A_MENOR);
					} else if (isPagamentoRealizadoAposVencimento(pagamentoMatera, pagamentoEntity)) {
						log.info("Pagamento vencido, Gerando inconsistencia...");
						//geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
						//		SubMotivoFonteRejeicao.VENCIDO, PGTO_VENCIDO);
						cancelaPagamento(pagamentoMatera, pagamentoEntity, pedidoEntity,
								SubMotivoFonteRejeicao.VENCIDO);
					} else {
						transacaoService.processaPagamentoAprovado(pagamentoMatera, pagamentoEntity, vendaEntity, pedidoEntity);
                        //
						Object o_itensProcessados = stepExecution.getExecutionContext().get("itensProcessados");
					    Integer itensProcessados = (o_itensProcessados==null) ? 1 : (Integer)o_itensProcessados;
					    itensProcessados++;
					    stepExecution.getExecutionContext().put("itensProcessados", itensProcessados);
                        //  
						Object o_valorTotalProcessado = stepExecution.getExecutionContext().get("valorTotalProcessado");
					    Double valorTotalProcessado = (o_valorTotalProcessado==null) ? 0 : (Double)o_valorTotalProcessado;
					    valorTotalProcessado+=pagamentoMatera.getValor();
					    stepExecution.getExecutionContext().put("valorTotalProcessado", valorTotalProcessado);
                        //					    
					    registroArquivoTiff = layoutTifService.getRegistroArquivoTiff(pagamentoEntity, pedidoEntity, itensProcessados, nomeFantasia);
					    log.info("### registroArquivoTiff: "+registroArquivoTiff.toString());
					}					
                    
				}
			} catch (Exception ex) {
				log.error("Erro inesperado com o registro :" + pagamentoMatera, ex);
				//geraInconsistencia(pagamentoMatera, pagamentoEntity, pedidoEntity, vendaEntity,
				//		SubMotivoFonteRejeicao.ERRO_SISTEMICO, ERRO_SISTEMICO);
			}

		}
		return registroArquivoTiff;
	}
/*	
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
*/
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
