package br.com.stelo.batch.pagamento.boleto.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pagamento {

    protected Integer codigoPagamento;
    protected String codigoAutorizador;
    protected Integer codigoProprietario;
    protected Long codigoTipoPagamento;
    protected Long codigoTransacao;
    protected Integer quantidadeParcelas;
    protected String status;
    protected Double valorCancelado;
    protected Double valorEstorno;
    protected Double valorPagamento;
    protected Double valorPago;
    protected Moeda moeda;
    protected Date vencimento;
    protected Integer codigoFonteRejeicao;
    protected Integer codigoMotivoRejeicao;
    protected Integer codigoSubMotivoRejeicao;
    protected Date data;
    protected Date dataPagamento;
    protected String numeroTarget;
    protected String idPagamentoEc;
    protected String numeroDocumentoPortador;
}
