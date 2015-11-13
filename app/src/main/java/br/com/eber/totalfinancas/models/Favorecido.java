package br.com.eber.totalfinancas.models;

import br.com.eber.totalfinancas.enuns.Ativo;

public class Favorecido {

    public static final String TABLE_NAME = "favorecido";
    public static final String[] COLUMNS = {"id_favorecido", "nm_favorecido", "bo_ativo"};
    public static final String NOME = "nm_favorecido";

    private int id;
    private String nome;
    private Ativo ativo;

    public Favorecido(String nome) {
        this.id = 0;
        this.nome = nome;
        this.ativo = Ativo.SIM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }
}
