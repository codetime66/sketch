package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatusCadastro {
    protected Long id;
    protected Long idMotivoBloqueio;

}
