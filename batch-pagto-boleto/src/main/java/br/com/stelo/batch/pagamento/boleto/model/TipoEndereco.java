package br.com.stelo.batch.pagamento.boleto.model;

public enum TipoEndereco {

    COMERCIAL("comercial"),
    FISICO_DA_FILIAL("fisico da filial"),
    COBRANCA("cobranca"),
    ENTREGA("entrega"),
    OUTRO("outro"),
    DISTRIBUIDORA("distribuidora"),
    RESIDENCIAL("residencial");
    private final String value;

    TipoEndereco(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoEndereco fromValue(String v) {
        for (TipoEndereco c: TipoEndereco.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
