package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Endereco {
    protected Long id;
    protected FonteDados fonteDados;
    protected TipoEndereco tipoEndereco;
    protected String logradouro;
    protected String bairro;
    protected String numero;
    protected String complemento;
    protected String cidade;
    protected Estado estado;
    protected String pais;
    protected String cep;
    protected String apelidoEndereco;
    protected String destinatario;
    protected Boolean preferencial;
    protected Boolean principal;
    protected Relacionamento relacionamento;
    protected Boolean incluirEndereco;
}
