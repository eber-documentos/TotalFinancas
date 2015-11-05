package br.com.eber.totalfinancas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
