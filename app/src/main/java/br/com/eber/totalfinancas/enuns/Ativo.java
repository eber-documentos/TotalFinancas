package br.com.eber.totalfinancas.enuns;

public enum Ativo {

    SIM("S"),
    NAO("N");

    private String value;

    Ativo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Ativo parse(String value) {

        Ativo ativo;
        if (SIM.getValue().equals(value)) {
            ativo = SIM;
        } else if (NAO.getValue().equals(value)) {
            ativo = NAO;
        } else {
            throw new IllegalArgumentException(value);
        }

        return ativo;
    }
}
