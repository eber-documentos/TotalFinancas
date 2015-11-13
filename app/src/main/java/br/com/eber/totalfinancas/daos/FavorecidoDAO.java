package br.com.eber.totalfinancas.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.eber.totalfinancas.helpers.DatabaseHelper;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoDAO {

    private DatabaseHelper databaseHelper;

    public FavorecidoDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public List<Favorecido> findAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        try {
            List<Favorecido> favorecidos = new ArrayList<>();

            Cursor cursor = db.query(Favorecido.TABLE_NAME, Favorecido.COLUMNS, null, null, null, null, Favorecido.NOME);
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    Favorecido favorecido = new Favorecido(cursor.getString(cursor.getColumnIndex(Favorecido.NOME)));
                    favorecidos.add(favorecido);
                } while (cursor.moveToNext());
            }

            return favorecidos;
        } finally {
            db.close();
        }
    }
}
