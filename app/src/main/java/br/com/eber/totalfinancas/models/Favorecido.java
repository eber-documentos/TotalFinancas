package br.com.eber.totalfinancas.models;

import android.os.Parcel;
import android.os.Parcelable;

import br.com.eber.totalfinancas.enuns.Ativo;

public class Favorecido implements Parcelable {

    private int id;
    private String nome;
    private Ativo ativo;

    public Favorecido() {
    }

    protected Favorecido(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        ativo = Ativo.parse(in.readString());
    }

    public static final Creator<Favorecido> CREATOR = new Creator<Favorecido>() {
        @Override
        public Favorecido createFromParcel(Parcel in) {
            return new Favorecido(in);
        }

        @Override
        public Favorecido[] newArray(int size) {
            return new Favorecido[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(ativo.getValue());
    }
}
