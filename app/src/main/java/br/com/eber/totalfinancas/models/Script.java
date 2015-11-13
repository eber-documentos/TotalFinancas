package br.com.eber.totalfinancas.models;

import org.simpleframework.xml.Element;

@Element(name = "script")
public class Script implements Comparable<Script> {

    @Element
    private int versao;

    @Element
    private int ordem;

    @Element
    private String sql;

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public int compareTo(Script another) {

        if (getVersao() < another.getVersao()) {
            return -1;
        }

        if (getVersao() > another.getVersao()) {
            return 1;
        }

        if (getOrdem() < another.getOrdem()) {
            return -1;
        }

        if (getOrdem() > another.getOrdem()) {
            return 1;
        }

        return 0;
    }
}
