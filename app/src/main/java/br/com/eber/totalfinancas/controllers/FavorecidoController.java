package br.com.eber.totalfinancas.controllers;

import android.content.Context;

import java.util.List;

import br.com.eber.totalfinancas.daos.FavorecidoDAO;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoController {

    private FavorecidoDAO dao;

    public FavorecidoController(Context context) {
        dao = new FavorecidoDAO(context);
    }

    public List<Favorecido> findAll() {
        return dao.findAll();
    }

    public void insert(Favorecido favorecido) {
        dao.insert(favorecido);
    }

    public void update(Favorecido favorecido) {
        dao.update(favorecido);
    }

    public void delete(Favorecido favorecido) {
        dao.delete(favorecido);
    }
}
