package br.com.eber.totalfinancas.models;

public class Favorecido {

    private Long codigo;
    private String nome;
    private Boolean ativo;

    public Favorecido(String nome) {
        this.codigo = 0L;
        this.nome = nome;
        this.ativo = true;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
