package br.com.eber.totalfinancas.activities;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.eber.totalfinancas.R;
import br.com.eber.totalfinancas.controllers.AbstractController;
import br.com.eber.totalfinancas.controllers.FavorecidoController;
import br.com.eber.totalfinancas.enuns.Ativo;
import br.com.eber.totalfinancas.enuns.Operacao;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoActivity extends AbstractActivity<Favorecido> {

    private EditText etNome;
    private CheckBox cbAtivo;

    @Override
    protected int getContentView() {
        return R.layout.activity_favorecido;
    }

    @Override
    protected void initComp() {
        etNome = (EditText) findViewById(R.id.etNome);
        cbAtivo = (CheckBox) findViewById(R.id.cbAtivo);
    }

    @Override
    protected AbstractController<Favorecido> newControllerInstance(Context context) {
        return new FavorecidoController(context);
    }

    @Override
    protected void onControlScreen(Operacao operacao) {
        boolean enabled = operacao == Operacao.INSERIR || operacao == Operacao.ALTERAR;
        etNome.setEnabled(enabled);
        cbAtivo.setEnabled(enabled);
    }

    @Override
    protected Favorecido newEntityInstance() {
        Favorecido favorecido = new Favorecido();
        favorecido.setAtivo(Ativo.SIM);
        return favorecido;
    }

    @Override
    protected void onDataToScreen(Operacao operacao, Favorecido obj) {
        etNome.setText(obj.getNome());
        cbAtivo.setChecked(obj.getAtivo() == Ativo.SIM ? true : false);
    }

    @Override
    protected void onScreenToData(Operacao operacao, Favorecido obj) {
        obj.setNome(etNome.getText().toString());
        obj.setAtivo(cbAtivo.isChecked() ? Ativo.SIM : Ativo.NAO);
    }
}
