package br.com.eber.totalfinancas.controllers;

import android.content.Context;

import java.util.List;

import br.com.eber.totalfinancas.daos.AbstractDAO;

public abstract class AbstractController<T> {

    private AbstractDAO<T> dao;

    protected abstract AbstractDAO<T> newDAOInstance(Context context);

    public AbstractController(Context context) {
        dao = newDAOInstance(context);
    }

    public AbstractDAO<T> getDAO() {
        return dao;
    }

    public void insert(T obj) {
        dao.insert(obj);
    }

    public void update(T obj) {
        dao.update(obj);
    }

    public void delete(T obj) {
        dao.delete(obj);
    }

    public List<T> findAll() {
        return dao.findAll();
    }
}
