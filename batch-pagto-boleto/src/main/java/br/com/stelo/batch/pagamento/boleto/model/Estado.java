package br.com.stelo.batch.pagamento.boleto.model;

public enum Estado {

    SC,
    PR,
    RS,
    SP,
    MS,
    MT,
    MG,
    RJ,
    ES,
    BA,
    PE,
    AC,
    AP,
    AM,
    DF,
    GO,
    PI,
    PA,
    RO,
    RR,
    TO,
    AL,
    CE,
    PB,
    RN,
    SE,
    MA;

    public String value() {
        return name();
    }

    public static Estado fromValue(String v) {
        return valueOf(v);
    }

}
