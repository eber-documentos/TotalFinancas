package br.com.eber.totalfinancas;


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


public class FavorecidoFragment extends Fragment {


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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FavorecidoRecyclerAdapter(favorecidos));

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favorecido = new Intent(getActivity(), FavorecidoActivity.class);
                startActivity(favorecido);
            }
        });

        return rootView;
    }
}
