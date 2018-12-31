package br.com.stelo.batch.pagamento.boleto.model;

import java.math.BigInteger;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MaquinaCartao {
    protected String numeroSerie;
    protected String codigoAtivacao;
    protected String statusMaquina;
    protected BigInteger status;
    protected Long sequencialMaquinaModuloTerminal;
    protected String codigoMaquinaModuloTerminal;
    protected BigInteger statusModuloTerminal;
    protected Date dataAquisicao;
    protected String modelo;
    protected Double valor;
    protected BigInteger codigoModelo;
    protected Boolean flagTransacaoRealizada;
    protected String chip;

}
