package br.com.eber.totalfinancas.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.eber.totalfinancas.enuns.Ativo;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoDAO extends AbstractDAO<Favorecido> {

    private static final String TABLE_NAME = "favorecido";
    private static final String COLUMN_ID = "id_favorecido";
    private static final String COLUMN_NOME = "nm_favorecido";
    private static final String COLUMN_ATIVO = "bo_ativo";
    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NOME, COLUMN_ATIVO};

    public FavorecidoDAO(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String[] getColumns() {
        return COLUMNS;
    }

    @Override
    protected Favorecido parseToInstance(Cursor cursor) {
        Favorecido favorecido = new Favorecido();
        favorecido.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        favorecido.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_NOME)));
        favorecido.setAtivo(Ativo.parse(cursor.getString(cursor.getColumnIndex(COLUMN_ATIVO))));
        return favorecido;
    }

    public void insert(Favorecido favorecido) {

        SQLiteDatabase db = getDatabaseHelper().getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NOME, favorecido.getNome());
            values.put(COLUMN_ATIVO, favorecido.getAtivo().getValue());
            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }

    public void update(Favorecido favorecido) {

        SQLiteDatabase db = getDatabaseHelper().getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NOME, favorecido.getNome());
            values.put(COLUMN_ATIVO, favorecido.getAtivo().getValue());
            db.update(TABLE_NAME, values, COLUMN_ID + " = " + favorecido.getId(), null);
        } finally {
            db.close();
        }
    }

    public void delete(Favorecido favorecido) {
        SQLiteDatabase db = getDatabaseHelper().getWritableDatabase();
        try {
            db.delete(TABLE_NAME, COLUMN_ID + " = " + favorecido.getId(), null);
        } finally {
            db.close();
        }
    }
}
