package br.com.stelo.batch.pagamento.boleto.model;

import java.math.BigInteger;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Terminal {
    protected Long id;
    protected Boolean terminalProprio;
    protected String codigoAdquirente;
    protected BigInteger statusTerminal;
    protected Date dataInstalacao;
    protected Long numeroTerminal;
    protected Long idTecnologia;
    protected Long idMarca;
    protected Long idModelo;
    protected Long idProtocolo;
    protected Moeda moeda;
    protected Double valorInstalacao;
    protected Long idTipoConexao;
    protected Boolean controlePagamentoMinimo;
    protected Long idTipoComercializacao;
    protected Double valor;
    protected Boolean processaVpay;
    protected MaquinasCartoes maquinasCartoes;
}
