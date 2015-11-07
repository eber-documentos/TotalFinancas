package br.com.eber.totalfinancas.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.eber.totalfinancas.R;

public class CategoriaFragment extends Fragment {


    public CategoriaFragment() {
    }

    public static CategoriaFragment newInstance() {
        CategoriaFragment categoriaFragment = new CategoriaFragment();
        return categoriaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categoria, container, false);
    }
}
