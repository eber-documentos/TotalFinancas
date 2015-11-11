package br.com.eber.totalfinancas.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.eber.totalfinancas.models.Favorecido;
import br.com.eber.totalfinancas.activities.FavorecidoActivity;
import br.com.eber.totalfinancas.adapters.FavorecidoRecyclerAdapter;
import br.com.eber.totalfinancas.R;


public class FavorecidoFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fab;

    public FavorecidoFragment() {
    }

    public static FavorecidoFragment newInstance() {
        FavorecidoFragment favorecidoFragment = new FavorecidoFragment();
        return favorecidoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorecido, container, false);

        List<Favorecido> favorecidos = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            favorecidos.add(new Favorecido("FAVORECIDO: " + i));
        }

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FavorecidoRecyclerAdapter(favorecidos));

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == fab) {
            Intent favorecido = new Intent(getActivity(), FavorecidoActivity.class);
            startActivity(favorecido);
        }
    }
}
