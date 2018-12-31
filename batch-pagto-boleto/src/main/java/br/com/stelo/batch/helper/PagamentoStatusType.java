package br.com.stelo.batch.helper;

import java.io.Serializable;

public enum PagamentoStatusType implements Serializable {

	PEDIDO_INICIADO("I"),
	AGUARDANDO_PAGTO("AP"),
	AUTORIZADO("AT"),
	EM_ANALISE_RISCO("ER"),
	APROVADO_RISCO("AR"),
	APROVADA("A"),
	EXPIRADA("E"),
	NEGADA_RISCO("NR"),
	NEGADA("N"),
	DESFEITA("D"),
	CANCELADA("C"),
	CANCELADA_PARCIAL("CP"),
	CONCLUIDO("CO"),
	EMCANCELAMENTO("EC"),
	EM_CANCELAMENTO_PARCIAL("EP"),
	CANCELADO_DUPLICIDADE("CD");

	private static final long serialVersionUID = 1L;

	private String value;

	private PagamentoStatusType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static PagamentoStatusType getType(final String value) {
		for (final PagamentoStatusType enumValue : values()) {
			if (enumValue.getValue().equals(value)) {
				return enumValue;
			}
		}
		return null;
	}
}