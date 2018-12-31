package br.com.stelo.batch.pagamento.boleto.model;

import java.io.Serializable;
import java.util.Date;

public class PagamentoMatera implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoRegistroMatera tipoRegistro;
	private String tipoPagamento;
	private String numeroDocumento;
	private Double valor;
	private Long idStelo;
	private Long codigoPedido;
	private String agencia;
	private String contaCorrente;
	private Date vencimento;
	private Date pagamento;

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(final String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(final String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(final Double valor) {
		this.valor = valor;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(final String agencia) {
		this.agencia = agencia;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(final String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(final Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getPagamento() {
		return pagamento;
	}

	public void setPagamento(final Date pagamento) {
		this.pagamento = pagamento;
	}

	public Long getIdStelo() {
		return idStelo;
	}

	public void setIdStelo(final Long idStelo) {
		this.idStelo = idStelo;
	}

	public Long getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(final Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public TipoRegistroMatera getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(final TipoRegistroMatera tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	@Override
	public String toString() {
		return "PagamentoMatera [tipoRegistro=" + tipoRegistro + ", tipoPagamento=" + tipoPagamento
				+ ", numeroDocumento=" + numeroDocumento + ", valor=" + valor + ", idStelo=" + idStelo
				+ ", codigoPedido=" + codigoPedido + ", agencia=" + agencia + ", contaCorrente=" + contaCorrente
				+ ", vencimento=" + vencimento + ", pagamento=" + pagamento + "]";
	}


}
