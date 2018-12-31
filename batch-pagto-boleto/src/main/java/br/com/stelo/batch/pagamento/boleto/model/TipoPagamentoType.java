package br.com.stelo.batch.pagamento.boleto.model;

import java.io.Serializable;

public enum TipoPagamentoType implements Serializable {

	CARTAO_DE_CREDITO(1, "CARTÃO DE CRÉDITO"),
	BOLETO(2, "BOLETO"),
	CARTAO_DE_DEBITO(3, "CARTÃO DE DÉBITO"),
	CONTA_PAGAMENTO(4, "CONTA PAGAMENTO"),
	P2P(5, "P2P"),
	BENEFICIO_ALIMENTACAO(6, "BENEFÍCIO - ALIMENTAÇÃO"),
	BENEFICIO_REFEICAO(7, "BENEFÍCIO - REFEIÇÃO"),
	PAGAMENTO_CELULAR(8, "PAGAMENTO CELULAR");

	private static final long serialVersionUID = 1L;

	private Integer value;

	private String description;

	private TipoPagamentoType(final Integer value, final String description) {
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static TipoPagamentoType getType(final Integer value) {
		for (final TipoPagamentoType enumValue : values()) {
			if (enumValue.getValue().equals(value)) {
				return enumValue;
			}
		}
		return null;
	}
}