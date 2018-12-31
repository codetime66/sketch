package br.com.stelo.batch.pagamento.boleto.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Relacionamento {
    protected Long id;
    protected Long idTipo;
    protected Long idPagamento;
    protected Date dataInicio;
    protected Date dataVencimento;
    protected Date dataFim;
    protected Long idEC;
    protected Boolean ativo;

}
