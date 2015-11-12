package br.com.eber.totalfinancas.parsers;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.eber.totalfinancas.models.Script;

public class ScriptXmlParser {

    private static final String NS = null;
    private static final String TAG_SCRIPTS = "scripts";
    private static final String TAG_SCRIPT = "script";
    private static final String TAG_VERSAO = "versao";
    private static final String TAG_ORDEM = "ordem";
    private static final String TAG_SQL = "sql";

    public List<Script> parse(InputStream in) throws IOException, XmlPullParserException {

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readScripts(parser);
        } finally {
            in.close();
        }
    }

    private List<Script> readScripts(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<Script> scripts = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, NS, TAG_SCRIPTS);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (TAG_SCRIPT.equals(name)) {
                scripts.add(readScript(parser));
            } else {
                skip(parser);
            }
        }

        return scripts;
    }

    private Script readScript(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, NS, TAG_SCRIPT);

        int versao = 0;
        int ordem = 0;
        String sql = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (TAG_VERSAO.equals(name)) {
                versao = readVersao(parser);
            } else if (TAG_ORDEM.equals(name)) {
                ordem = readOrdem(parser);
            } else if (TAG_SQL.equals(name)) {
                sql = readSql(parser);
            } else {
                skip(parser);
            }
        }

        return new Script(versao, ordem, sql);
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {

        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private int readVersao(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, NS, TAG_VERSAO);
        int versao = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, NS, TAG_VERSAO);
        return versao;
    }

    private int readOrdem(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, NS, TAG_ORDEM);
        int ordem = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, NS, TAG_ORDEM);
        return ordem;
    }

    private String readSql(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, NS, TAG_SQL);
        String sql = readText(parser);
        parser.require(XmlPullParser.END_TAG, NS, TAG_SQL);
        return sql;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String text = "";
        if (parser.next() == XmlPullParser.TEXT) {
            text = parser.getText();
            parser.nextTag();
        }

        return text;
    }
}
