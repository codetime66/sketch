package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Entrega {
    protected Endereco enderecoEntrega;
    protected Integer previsaoEntrega;
    protected String status;
    protected Double valorFrete;
    protected Boolean retirarLoja;
    protected String metodoEnvio;

}
