package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Item {
    protected Integer codigo;
    protected String descricao;
    protected String nome;
    protected Integer quantidade;
    protected Integer quantidadeCancelado;
    protected Integer quantidadeEstorno;
    protected String sku;
    protected String status;
    protected Double valorTotal;
    protected Double valorUnitario;
    protected Long idOferta;
    protected Long idVariacao;

}
