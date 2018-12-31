package br.com.stelo.batch.helper;

import java.io.Serializable;

public class MensagemDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String mensagem;

	public MensagemDomain() {
		super();
	}

	public MensagemDomain(final String codigo, final Object... params) {
		this.codigo = codigo;
		mensagem = BundleHandler.getValue(codigo, params);
	}

	public MensagemDomain(final String codigo) {
		this.codigo = codigo;
		mensagem = BundleHandler.getValue(codigo);
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setMensagem(final String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}