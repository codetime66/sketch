package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CadastroExterno {
    protected String id;
    protected Boolean ativo;

}
