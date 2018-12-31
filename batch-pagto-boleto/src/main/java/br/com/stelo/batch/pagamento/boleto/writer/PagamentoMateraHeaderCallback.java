package br.com.stelo.batch.pagamento.boleto.writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

import br.com.stelo.batch.helper.FixedLengthField;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class PagamentoMateraHeaderCallback implements FlatFileHeaderCallback {

	private Date dataExecucao;
	private int sequencial = 0;        

    @Override
    public void writeHeader(Writer writer) throws IOException {
    	dataExecucao = new Date();
        writer.append("00");
		writer.append(FixedLengthField.to(8).setDate(dataExecucao, "yyyyMMdd").toString());
		writer.append(FixedLengthField.to(5, FixedLengthField.Behaviour.LEFT_ZERO).setInt(sequencial).toString());
        
    }
}
