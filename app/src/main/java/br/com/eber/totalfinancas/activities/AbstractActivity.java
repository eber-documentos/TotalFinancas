package br.com.eber.totalfinancas.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.eber.totalfinancas.R;
import br.com.eber.totalfinancas.controllers.AbstractController;
import br.com.eber.totalfinancas.enuns.Operacao;

public abstract class AbstractActivity<T> extends AppCompatActivity {

    public static final String ENTITY = "entity";

    private ActionBar actionBar;
    private Operacao operacao;
    private AbstractController<T> controller;
    private T obj;

    protected abstract int getContentView();

    protected abstract void initComp();

    protected abstract AbstractController<T> newControllerInstance(Context context);

    protected abstract void onControlScreen(Operacao operacao);

    protected abstract T newEntityInstance();

    protected abstract void onDataToScreen(Operacao operacao, T obj);

    protected abstract void onScreenToData(Operacao operacao, T obj);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initComp();

        controller = newControllerInstance(this);

        Intent i = getIntent();
        if (i == null) {
            throw new IllegalArgumentException(getString(R.string.intentNaoInformada));
        }

        String o = i.getStringExtra(Operacao.asString());
        if (o == null) {
            throw new IllegalArgumentException(getString(R.string.operacaoNaoInformada));
        }

        setOperacao(Operacao.parse(o));
        if (operacao == Operacao.INSERIR) {
            obj = newEntityInstance();
        } else {
            obj = i.getParcelableExtra(ENTITY);
        }

        if (obj == null) {
            throw new IllegalArgumentException(getString(R.string.entidadeNaoDefinida));
        }

        onControlScreen(operacao);
        onDataToScreen(operacao, obj);
        setResult(RESULT_CANCELED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (operacao == Operacao.VISUALIZAR) {
            getMenuInflater().inflate(R.menu.abstract_visualizacao, menu);
        } else {
            getMenuInflater().inflate(R.menu.abstract_manutencao, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home || id == R.id.action_cancelar) {
            finish();
            return true;
        }

        if (id == R.id.action_confirmar) {

            try {
                if (operacao == Operacao.INSERIR || operacao == Operacao.ALTERAR) {
                    onScreenToData(operacao, obj);
                }

                save();

                setResult(RESULT_OK);
                finish();
            } catch (Exception e) {
                onConfirmException(operacao, obj, e);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setOperacao(Operacao operacao) {
        this.operacao = operacao;
        actionBar.setSubtitle(getSubtitle());
        invalidateOptionsMenu();
    }

    private String getSubtitle() {

        String subtitle;
        if (operacao == Operacao.INSERIR) {
            subtitle = getString(R.string.inserindo);
        } else if (operacao == Operacao.ALTERAR) {
            subtitle = getString(R.string.alterando);
        } else if (operacao == Operacao.EXCLUIR) {
            subtitle = getString(R.string.excluindo);
        } else {
            subtitle = getString(R.string.visualizando);
        }

        return subtitle;
    }

    private void save() {
        if (operacao == Operacao.INSERIR) {
            controller.insert(obj);
        } else if (operacao == Operacao.ALTERAR) {
            controller.update(obj);
        } else {
            controller.delete(obj);
        }
    }

    protected void onConfirmException(Operacao operacao, T obj, Exception e) {
        Toast.makeText(this, getString(R.string.erroAoConfirmar) + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
