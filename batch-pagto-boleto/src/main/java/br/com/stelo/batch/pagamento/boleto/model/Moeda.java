package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Moeda {
    protected Long id;
    protected String codigoMoeda;
    protected String descricao;
    protected String simbolo;
    protected Integer quantidadeDigitosDecimais;

}
