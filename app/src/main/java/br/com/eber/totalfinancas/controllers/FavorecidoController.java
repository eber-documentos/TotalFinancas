package br.com.eber.totalfinancas.controllers;

import android.content.Context;

import br.com.eber.totalfinancas.daos.AbstractDAO;
import br.com.eber.totalfinancas.daos.FavorecidoDAO;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoController extends AbstractController<Favorecido> {

    public FavorecidoController(Context context) {
        super(context);
    }

    @Override
    protected AbstractDAO<Favorecido> newDAOInstance(Context context) {
        return new FavorecidoDAO(context);
    }
}
