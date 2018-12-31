package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Parcelamento {

    protected Long codigoParcelamento;
    protected Long numeroParcela;
    protected Double valor;

}
