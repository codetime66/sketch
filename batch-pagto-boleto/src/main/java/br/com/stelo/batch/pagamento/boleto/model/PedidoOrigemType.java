package br.com.stelo.batch.pagamento.boleto.model;

public enum PedidoOrigemType {

	WALLET("W"),

	SUBADQUIRENTE("S"),
	
	MPOS("M"),

	M1("M1");

	private static final long serialVersionUID = 1L;

	private String value;

	private PedidoOrigemType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static PedidoOrigemType getType(final String value) {
		for (final PedidoOrigemType enumValue : values()) {
			if (enumValue.getValue().equals(value)) {
				return enumValue;
			}
		}
		return null;
	}
}