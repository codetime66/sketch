package br.com.stelo.batch.pagamento.boleto.writer;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.stelo.batch.helper.FixedLengthField;


import java.io.IOException;
import java.io.Writer;
import java.util.Date;
//import java.util.Set;
//import java.util.Map.Entry;

public class PagamentoMateraHeaderCallback implements FlatFileHeaderCallback {

	private static final Logger log = LoggerFactory.getLogger(PagamentoMateraHeaderCallback.class);
	
    @Value("#{StepExecution}")
    private StepExecution stepExecution;
	
	private Date dataExecucao;

    @Override
    public void writeHeader(Writer writer) throws IOException {
    	
		//Set<Entry<String, Object>> es = stepExecution.getExecutionContext().entrySet();
		//es.forEach(el -> log.info("### key="+el.getKey()+" , value="+el.getValue()));
    	
    	dataExecucao = new Date();
    	
		String nomeArquivo = stepExecution.getExecutionContext().getString("tiffFileName");
		
		nomeArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf('/') + 1);
		
		log.info("### nomeArquivo="+nomeArquivo);

		writer.append("10");
		writer.append(FixedLengthField.to(9, FixedLengthField.Behaviour.LEFT_ZERO).setString("1").toString());
		writer.append(FixedLengthField.to(14, FixedLengthField.Behaviour.LEFT_ZERO).setDate(dataExecucao, "yyyyMMddHHmmss").toString());
		writer.append(FixedLengthField.to(60, FixedLengthField.Behaviour.RIGHT_BLANK).setString(nomeArquivo).toString());
		
    }
}
