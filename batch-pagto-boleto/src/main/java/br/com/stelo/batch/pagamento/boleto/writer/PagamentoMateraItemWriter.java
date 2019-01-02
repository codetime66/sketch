package br.com.stelo.batch.pagamento.boleto.writer;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import br.com.stelo.batch.pagamento.boleto.model.RegistroArquivoTiff;

import java.util.ArrayList;
import java.util.List;

@Component
public class PagamentoMateraItemWriter implements ItemWriter<RegistroArquivoTiff> {

        private static final Logger log = LoggerFactory.getLogger(PagamentoMateraItemWriter.class);
	
        @Getter
        private List<RegistroArquivoTiff> pagamentos = new ArrayList<RegistroArquivoTiff>();

		@Override
		public void write(List<? extends RegistroArquivoTiff> items) throws Exception {
			
			log.info("PagamentoMateraItemWriter.write...");
			items.forEach(pagamento -> log.info("###PagamentoMatera: "+pagamento.toString()));
			
			pagamentos.addAll(items);
		}
}
