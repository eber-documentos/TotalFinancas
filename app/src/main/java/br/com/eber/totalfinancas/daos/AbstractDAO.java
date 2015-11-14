package br.com.eber.totalfinancas.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.eber.totalfinancas.helpers.DatabaseHelper;

public abstract class AbstractDAO<T> {
    private Context context;
    private DatabaseHelper databaseHelper;

    public AbstractDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    protected Context getContext() {
        return context;
    }

    protected DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    protected abstract String getTableName();
    protected abstract String[] getColumns();
    protected abstract T parseToInstance(Cursor cursor);

    public List<T> findAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        try {
            List<T> list = new ArrayList<>();

            Cursor cursor = db.query(getTableName(), getColumns(), null, null, null, null, null);
            if (cursor != null) {

                try {
                    if (cursor.moveToFirst()) {
                        do {
                            T t = parseToInstance(cursor);
                            list.add(t);
                        } while (cursor.moveToNext());
                    }
                } finally {
                    cursor.close();
                }
            }

            return list;
        } finally {
            db.close();
        }
    }
}
