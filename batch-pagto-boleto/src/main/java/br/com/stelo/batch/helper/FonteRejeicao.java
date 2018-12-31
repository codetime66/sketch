package br.com.stelo.batch.helper;

public enum FonteRejeicao {
	STELO(1, "Stelo"), HP(2, "HP"), EC(3, "EC"), COMPRADOR(4, "Comprador"), CARTAO_PROTEGIDO(5, "Cartao Protegido"),
	SOFTWARE_EXPRESS(6, "Software Express");

	private Integer value;

	private String descricao;

	private FonteRejeicao(final Integer value, final String descricao) {
		this.value = value;
		this.descricao = descricao;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescricao() {
		return descricao;
	}

	public static FonteRejeicao getValue(final Integer id) {
		if (id != null) {
			final FonteRejeicao[] values = FonteRejeicao.values();
			for (final FonteRejeicao value2 : values) {
				if (value2.compare(id)) {
					return value2;
				}
			}
		}
		return null;
	}

	public static Integer getValueFonteRejeicao(final Integer idEnum) {
		if (idEnum != null) {
			final FonteRejeicao dados = getValue(idEnum);
			if (dados != null) {
				return getValue(idEnum).getValue();
			}
		}
		return -1;
	}

	public boolean compare(final Integer i) {
		return value == i.intValue();
	}
}
