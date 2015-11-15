package br.com.eber.totalfinancas.enuns;

import br.com.eber.totalfinancas.R;

public enum Operacao {

    INSERIR("inserir"),
    ALTERAR("alterar"),
    EXCLUIR("excluir"),
    VISUALIZAR("visualizar");

    private String value;

    Operacao(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Operacao parse(String value) {

        Operacao operacao;
        if (INSERIR.getValue().equals(value)) {
            operacao = INSERIR;
        } else if (ALTERAR.getValue().equals(value)) {
            operacao = ALTERAR;
        } else if (EXCLUIR.getValue().equals(value)) {
            operacao = EXCLUIR;
        } else if (VISUALIZAR.getValue().equals(value)) {
            operacao = VISUALIZAR;
        } else {
            throw new IllegalArgumentException("Operação inválida: " + value);
        }

        return operacao;
    }

    public static String asString() {
        return "operacao";
    }
}
