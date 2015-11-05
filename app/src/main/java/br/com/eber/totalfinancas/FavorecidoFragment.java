package br.com.eber.totalfinancas;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
