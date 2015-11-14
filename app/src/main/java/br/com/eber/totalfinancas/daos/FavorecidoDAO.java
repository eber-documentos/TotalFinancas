package br.com.eber.totalfinancas.daos;

import android.content.Context;
import android.database.Cursor;

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
}
