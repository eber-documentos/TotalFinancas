package br.com.eber.totalfinancas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.eber.totalfinancas.R;
import br.com.eber.totalfinancas.controllers.FavorecidoController;
import br.com.eber.totalfinancas.enuns.Ativo;
import br.com.eber.totalfinancas.enuns.Operacao;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoActivity extends AbstractActivity {

    private EditText etNome;
    private CheckBox cbAtivo;
    private Favorecido favorecido;

    private FavorecidoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorecido);

        etNome = (EditText) findViewById(R.id.etNome);
        cbAtivo = (CheckBox) findViewById(R.id.cbAtivo);

        controller = new FavorecidoController(this);

        Intent i = getIntent();
        if (i != null) {

            favorecido = i.getParcelableExtra(Favorecido.class.getSimpleName());
            if (favorecido != null) {
                etNome.setText(favorecido.getNome());
                cbAtivo.setChecked(favorecido.getAtivo() == Ativo.SIM ? true : false);
            } else {
                favorecido = new Favorecido();
            }
        }
    }


    @Override
    protected void setDataToScreen() {

    }

    @Override
    protected void onClearCampos() {

    }

    @Override
    protected void onBloquearDesbloquearCampos(boolean bloquear) {

    }

    @Override
    protected boolean onConfirmar(Operacao operacao) {

        favorecido.setNome(etNome.getText().toString());
        favorecido.setAtivo(cbAtivo.isChecked() ? Ativo.SIM : Ativo.NAO);

        if (operacao == Operacao.INSERIR) {
            controller.insert(favorecido);
        } else if (operacao == Operacao.ALTERAR) {
            controller.update(favorecido);
        }

        return true;
    }
}
