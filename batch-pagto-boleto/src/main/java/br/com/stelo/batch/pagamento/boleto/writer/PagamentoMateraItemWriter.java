package br.com.stelo.batch.pagamento.boleto.writer;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import br.com.stelo.batch.pagamento.boleto.model.PagamentoMateraProc;

import java.util.ArrayList;
import java.util.List;

@Component
public class PagamentoMateraItemWriter implements ItemWriter<PagamentoMateraProc> {

        private static final Logger log = LoggerFactory.getLogger(PagamentoMateraItemWriter.class);
	
        @Getter
        private List<PagamentoMateraProc> pagamentos = new ArrayList<PagamentoMateraProc>();

		@Override
		public void write(List<? extends PagamentoMateraProc> items) throws Exception {
			
			log.info("PagamentoMateraItemWriter.write...");
			items.forEach(pagamento -> log.info("###PagamentoMatera: "+pagamento.toString()));
			
			pagamentos.addAll(items);
		}
}
