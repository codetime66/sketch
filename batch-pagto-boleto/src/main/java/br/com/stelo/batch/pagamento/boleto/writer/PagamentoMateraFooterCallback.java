package br.com.stelo.batch.pagamento.boleto.writer;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import br.com.stelo.batch.helper.FixedLengthField;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class PagamentoMateraFooterCallback extends StepExecutionListenerSupport implements FlatFileFooterCallback {

        private static final String LINE_SEPARATOR = System.lineSeparator();
        private StepExecution stepExecution;

    	private int quantidadeLinhasEscritas = 0;
    	private Date dataExecucao;
    	private int sequencial = 0;        
        
    	@Override
        public void writeFooter(Writer writer) throws IOException {
                //writer.write("# Write count: " + stepExecution.getWriteCount());
                //writer.write(LINE_SEPARATOR);
                //long delta = stepExecution.getEndTime().getTime() - stepExecution.getStartTime().getTime();
                //writer.write("# Done in: " + delta + " ms");
        	
        	dataExecucao = new Date();
        	
    		writer.append("99");
    		writer.append(FixedLengthField.to(8).setDate(dataExecucao, "yyyyMMdd").toString());
    		writer.append(FixedLengthField.to(5, FixedLengthField.Behaviour.LEFT_ZERO).setInt(sequencial).toString());
    		writer.append(FixedLengthField.to(6, FixedLengthField.Behaviour.LEFT_ZERO).setInt(quantidadeLinhasEscritas).toString());
        	
        }

        @Override
        public void beforeStep(StepExecution stepExecution) {
                this.stepExecution = stepExecution;
        }
}
