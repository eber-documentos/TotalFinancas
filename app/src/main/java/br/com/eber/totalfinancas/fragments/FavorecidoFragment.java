package br.com.eber.totalfinancas.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.eber.totalfinancas.R;
import br.com.eber.totalfinancas.activities.FavorecidoActivity;
import br.com.eber.totalfinancas.adapters.FavorecidoRecyclerAdapter;
import br.com.eber.totalfinancas.controllers.FavorecidoController;
import br.com.eber.totalfinancas.models.Favorecido;


public class FavorecidoFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fab;
    private FavorecidoController controller;
    private List<Favorecido> favorecidos;
    private RecyclerView recyclerView;
    FavorecidoRecyclerAdapter adapter;

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

        controller = new FavorecidoController(getActivity());
        favorecidos = controller.findAll();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FavorecidoRecyclerAdapter(getActivity(), this, controller, favorecidos);
        recyclerView.setAdapter(adapter);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == fab) {
            Intent favorecido = new Intent(getActivity(), FavorecidoActivity.class);
            favorecido.putExtra("operacao", "inserir");
            startActivityForResult(favorecido, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                favorecidos = controller.findAll();
                adapter.setFavorecidos(favorecidos);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
