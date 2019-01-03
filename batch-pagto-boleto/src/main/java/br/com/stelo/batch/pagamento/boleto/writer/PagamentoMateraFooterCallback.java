package br.com.stelo.batch.pagamento.boleto.writer;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.stelo.batch.helper.FixedLengthField;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
//import java.util.Map.Entry;
//import java.util.Set;

public class PagamentoMateraFooterCallback implements FlatFileFooterCallback {

	    @Value("#{StepExecution}")
        private StepExecution stepExecution;
	
	    private static final Logger log = LoggerFactory.getLogger(PagamentoMateraFooterCallback.class);
	
    	private Date dataExecucao;
        
    	@Override
        public void writeFooter(Writer writer) throws IOException {
        	
    		//Set<Entry<String, Object>> es = stepExecution.getExecutionContext().entrySet();
    		//es.forEach(el -> log.info("### key="+el.getKey()+" , value="+el.getValue()));
    		
    		Object o_valorTotalProcessado = stepExecution.getExecutionContext().get("valorTotalProcessado");
		    Double valorTotalProcessado = (o_valorTotalProcessado==null) ? 0 : (Double)o_valorTotalProcessado;
    		
    		final String vlrTotalProcessadoFormatado = formatarValorTotalProcessado(valorTotalProcessado);
    		
        	dataExecucao = new Date();
        	
        	int quantidadeLinhasEscritas=Integer.parseInt( stepExecution.getExecutionContext().get("FlatFileItemWriter.written").toString() );
        	
    		String nomeArquivo = stepExecution.getExecutionContext().getString("tiffFileName");
    		
    		nomeArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf('/') + 1);
    		
    		log.info("### nomeArquivo="+nomeArquivo);

    		writer.append("31");
    		writer.append(FixedLengthField.to(9, FixedLengthField.Behaviour.LEFT_ZERO).setInt(quantidadeLinhasEscritas + 2).toString());
    		writer.append(FixedLengthField.to(14, FixedLengthField.Behaviour.LEFT_ZERO).setDate(dataExecucao, "yyyyMMddHHmmss").toString());
    		writer.append(FixedLengthField.to(9, FixedLengthField.Behaviour.LEFT_ZERO).setInt(quantidadeLinhasEscritas + 2).toString());
    		writer.append(FixedLengthField.to(16, FixedLengthField.Behaviour.LEFT_ZERO).setString(vlrTotalProcessadoFormatado).toString());
    		writer.append(FixedLengthField.to(60, FixedLengthField.Behaviour.RIGHT_BLANK).setString(nomeArquivo).toString());
        	
        }

    	protected String formatarValorTotalProcessado(final Double valorTotalProcessado) {

    		Double vlrParaFormatar = valorTotalProcessado;

    		if (vlrParaFormatar == null) {
    			vlrParaFormatar = 0.0;
    		}

    		BigDecimal bd = new BigDecimal(vlrParaFormatar);
    		bd = bd.setScale(2, RoundingMode.HALF_UP);

    		vlrParaFormatar = bd.doubleValue();

    		final Locale locale = new Locale("en", "UK");
    		final String pattern = "##.0000";

    		final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
    		decimalFormat.applyPattern(pattern);

    		return decimalFormat.format(vlrParaFormatar).replaceAll("\\.", "");
    	}

}
