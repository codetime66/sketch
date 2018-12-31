package br.com.stelo.batch.pagamento.boleto.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Venda {
    protected Pessoa vendedor;
    protected String status;
    protected String codigoPedidoEstabelecimentoComercial;
    protected String descricaoPedidoEstabelecimentoComercial;
    protected Double valorTotal;
    protected List<Item> itens;
    protected Entrega entrega;
    protected Pagamento pagamento;
    protected Pessoa comprador;
    protected Double valorDesconto;

}
