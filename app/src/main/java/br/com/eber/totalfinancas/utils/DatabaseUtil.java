package br.com.eber.totalfinancas.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.eber.totalfinancas.models.Database;
import br.com.eber.totalfinancas.models.Script;

public class DatabaseUtil {

    private static final String TOTAL_FINANCAS = "totalfinancas";
    private static final String DATABASE = "database.xml";

    private Context context;

    public DatabaseUtil(Context context) {
        this.context = context;
    }

    public void execute(SQLiteDatabase db, int oldVersion, int newVersion) {
        List<Script> scripts = getScripts(oldVersion, newVersion);

        for (Script script : scripts) {
            db.execSQL(script.getSql());
        }
    }

    public void execute(SQLiteDatabase db, int version) {
        execute(db, version - 1, version);
    }

    private List<Script> getScripts(int oldVersion, int newVersion) {

        List<Script> scripts = new ArrayList<Script>();

        try {
            InputStream file = getDatabaseFile();
            try {
                Serializer serializer = new Persister();
                Database database = serializer.read(Database.class, file, false);

                for (Script script : database.getScripts()) {

                    if (script.getVersao() > oldVersion && script.getVersao() <= newVersion) {
                        scripts.add(script);
                    }
                }
            } finally {
                file.close();
            }
        } catch (Exception e) {
            Log.e(TOTAL_FINANCAS, e.getMessage());
        }

        Collections.sort(scripts);
        return scripts;
    }

    private InputStream getDatabaseFile() {

        try {
            return context.getAssets().open(DATABASE);
        } catch (IOException e) {
            Log.e(TOTAL_FINANCAS, e.getMessage());
        }

        return null;
    }
}
