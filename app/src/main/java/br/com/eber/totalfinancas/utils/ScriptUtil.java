package br.com.eber.totalfinancas.utils;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.eber.totalfinancas.models.Script;

public class ScriptUtil {

    public static void execute(SQLiteDatabase db, int oldVersion, int newVersion) {
        List<Script> scripts = getScripts(oldVersion, newVersion);

        for (Script script : scripts) {
            db.execSQL(script.getSql());
        }
    }

    public static void execute(SQLiteDatabase db, int version) {
        execute(db, version, version);
    }

    private static List<Script> getScripts(int oldVersion, int newVersion) {
        List<Script> scripts = new ArrayList<Script>();


        Collections.sort(scripts);
        return scripts;
    }
}
