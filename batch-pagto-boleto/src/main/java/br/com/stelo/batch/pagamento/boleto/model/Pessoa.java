package br.com.stelo.batch.pagamento.boleto.model;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pessoa {
    protected Long idStelo;
    protected String idVip;
    protected List<Relacionamento> relacionamento;
    protected Date dataCadastroContaStelo;
    protected Canal canalOrigem;
    protected StatusCadastro statusCadastro;
    protected String apelido;
    protected Boolean ativo;
    protected CadastroExterno cadastroContaPagamento;
    protected Boolean flagCadastroIncompleto;

}
