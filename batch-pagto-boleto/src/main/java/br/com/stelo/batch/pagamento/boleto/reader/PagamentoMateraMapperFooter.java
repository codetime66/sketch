package br.com.stelo.batch.pagamento.boleto.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import br.com.stelo.batch.pagamento.boleto.model.PagamentoMatera;
import br.com.stelo.batch.pagamento.boleto.model.TipoRegistroMatera;

public class PagamentoMateraMapperFooter implements
	FieldSetMapper<PagamentoMatera> {

    private static final Logger log = LoggerFactory.getLogger(PagamentoMateraMapperFooter.class);
	
	@Override
	public PagamentoMatera mapFieldSet(final FieldSet paramFieldSet)
		throws BindException {

		log.debug("footer descartado no processamento");
		final PagamentoMatera p = new PagamentoMatera();
		p.setTipoRegistro(TipoRegistroMatera.FOOTER);
		return p;
	}

}
