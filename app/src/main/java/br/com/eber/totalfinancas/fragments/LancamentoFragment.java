package br.com.eber.totalfinancas.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.eber.totalfinancas.R;


public class LancamentoFragment extends Fragment {


    public LancamentoFragment() {
    }

    public static LancamentoFragment newInstance() {
        LancamentoFragment lancamentoFragment = new LancamentoFragment();
        return lancamentoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lancamento, container, false);
    }
}
