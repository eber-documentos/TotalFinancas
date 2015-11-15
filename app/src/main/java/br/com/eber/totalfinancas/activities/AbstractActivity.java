package br.com.eber.totalfinancas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.eber.totalfinancas.R;
import br.com.eber.totalfinancas.enuns.Operacao;

public abstract class AbstractActivity extends AppCompatActivity {

    private Operacao operacao = Operacao.VISUALIZAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent i = getIntent();
        if (i != null) {

            String o = i.getStringExtra(Operacao.asString());
            if (o != null) {
                operacao = Operacao.parse(o);
            } else {
                throw new IllegalArgumentException(getString(R.string.operacaoNaoInformada));
            }
        } else {
            throw new IllegalArgumentException(getString(R.string.operacaoNaoInformada));
        }

        invalidateOptionsMenu();
        setResult(RESULT_CANCELED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (operacao == Operacao.INSERIR || operacao == Operacao.ALTERAR) {
            getMenuInflater().inflate(R.menu.abstract_manutencao, menu);
        } else {
            getMenuInflater().inflate(R.menu.abstract_, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        if (id == R.id.action_inserir) {
            if (operacao == Operacao.VISUALIZAR) {
                onBloquearDesbloquearCampos(false);
            }

            onClearCampos();

            operacao = Operacao.INSERIR;
            invalidateOptionsMenu();
            return true;
        }

        if (id == R.id.action_alterar) {
            if (operacao == Operacao.VISUALIZAR) {
                onBloquearDesbloquearCampos(false);
            }

            operacao = Operacao.ALTERAR;
            invalidateOptionsMenu();
            return true;
        }

        if (id == R.id.action_excluir) {
            // TODO: 15/11/2015 implementar excluir
        }

        if (id == R.id.action_confirmar) {

            boolean result = onValidate(operacao);

            if (result) {

                result = onConfirmar(operacao);

                if (result) {
                    setResult(RESULT_OK);
                    finish();
                }
            }

            return true;
        }

        if (id == R.id.action_cancelar) {
            onBloquearDesbloquearCampos(true);
            setDataToScreen();
            operacao = Operacao.VISUALIZAR;
            invalidateOptionsMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract void setDataToScreen();

    protected abstract void onClearCampos();

    protected abstract void onBloquearDesbloquearCampos(boolean bloquear);

    protected abstract boolean onConfirmar(Operacao operacao);

    protected boolean onValidate(Operacao operacao) {
        return true;
    }
}
