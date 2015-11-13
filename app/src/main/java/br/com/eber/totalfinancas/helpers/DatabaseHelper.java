package br.com.eber.totalfinancas.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.eber.totalfinancas.utils.DatabaseUtil;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "totalfinancas.db";
    private static final int VERSION = 1;

    private DatabaseUtil database;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        database = new DatabaseUtil(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database.execute(db, VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        database.execute(db, oldVersion, newVersion);
    }
}
