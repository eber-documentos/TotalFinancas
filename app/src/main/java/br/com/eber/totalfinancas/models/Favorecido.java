package br.com.eber.totalfinancas.models;

import br.com.eber.totalfinancas.enuns.Ativo;

public class Favorecido {

    private int id;
    private String nome;
    private Ativo ativo;

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
