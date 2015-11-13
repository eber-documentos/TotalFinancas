package br.com.eber.totalfinancas.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.eber.totalfinancas.R;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoRecyclerAdapter extends RecyclerView.Adapter<FavorecidoRecyclerAdapter.ViewHolder> {

    private List<Favorecido> favorecidos;

    public FavorecidoRecyclerAdapter(List<Favorecido> favorecidos) {
        this.favorecidos = favorecidos;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_favorecido, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Favorecido favorecido = favorecidos.get(position);
        holder.textView.setText(favorecido.getNome());
    }

    @Override
    public int getItemCount() {
        return favorecidos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView  = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
