package br.com.stelo.batch.pagamento.boleto.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import br.com.stelo.batch.helper.Converter;
import br.com.stelo.batch.pagamento.boleto.model.PagamentoMatera;
import br.com.stelo.batch.pagamento.boleto.model.TipoRegistroMatera;

public class PagamentoMateraMapper implements FieldSetMapper<PagamentoMatera> {

    private static final Logger log = LoggerFactory.getLogger(PagamentoMateraMapper.class);
	
	@Override
	public PagamentoMatera mapFieldSet(final FieldSet fieldSet) throws BindException {

		try {
			final PagamentoMatera pagamentoMatera = new PagamentoMatera();
			pagamentoMatera.setTipoRegistro(TipoRegistroMatera.DETALHE);
			pagamentoMatera.setTipoPagamento(fieldSet.readString("tipoPagamento"));
			pagamentoMatera.setNumeroDocumento(fieldSet.readString("numeroDocumento"));
			pagamentoMatera.setIdStelo(fieldSet.readLong("idStelo"));
			pagamentoMatera.setCodigoPedido(fieldSet.readLong("codigoPedido"));
			pagamentoMatera.setAgencia(fieldSet.readString("agencia"));
			pagamentoMatera.setContaCorrente(fieldSet.readString("contaCorrente"));
			pagamentoMatera.setValor(Converter.convertToBigDecimal(fieldSet.readString("valor"), 2).doubleValue());
			pagamentoMatera.setVencimento(fieldSet.readDate("vencimento", "yyyyMMdd"));
			pagamentoMatera.setPagamento(fieldSet.readDate("pagamento", "yyyyMMdd"));
			return pagamentoMatera;
		} catch (Exception e) {
			
			log.error("Erro ao mapear registro do arquivo da Matera: " + fieldSet.toString(), e);
			throw new BindException(fieldSet, "Arquivo Matera (registro com erro)");
		}
	}
}
