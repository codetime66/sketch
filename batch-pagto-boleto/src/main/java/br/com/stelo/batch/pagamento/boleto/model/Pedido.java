package br.com.stelo.batch.pagamento.boleto.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pedido {

    protected Long codigoPedido;
    protected Integer codigoPlataforma;
    protected String origem;
    protected String tipoOrigem;
    protected String status;
    protected Date dataInclusao;
    protected String numeroReferencia;
    protected Integer valorTaxaJurosParcela;
    protected Integer valorReferencia;
    protected String enderecoNotificacaoEc;
    protected String nsuStelo;
    protected String tidStelo;
    protected String codigoTransacaoLogErro;
    protected String idTransacaoCanalM1;
    protected String orderUrl;
    protected Integer codigoLojaOrigem;
    protected Venda venda;
}
