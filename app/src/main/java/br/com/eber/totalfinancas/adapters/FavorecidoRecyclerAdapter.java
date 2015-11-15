package br.com.eber.totalfinancas.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.eber.totalfinancas.R;
import br.com.eber.totalfinancas.activities.FavorecidoActivity;
import br.com.eber.totalfinancas.controllers.FavorecidoController;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoRecyclerAdapter extends RecyclerView.Adapter<FavorecidoRecyclerAdapter.ViewHolder> {

    private Context context;
    private Fragment fragment;
    FavorecidoController controller;
    private List<Favorecido> favorecidos;

    public FavorecidoRecyclerAdapter(Context context, Fragment fragment, FavorecidoController controller, List<Favorecido> favorecidos) {
        this.context = context;
        this.fragment = fragment;
        this.controller = controller;
        this.favorecidos = favorecidos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_row_favorecido, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Favorecido favorecido = favorecidos.get(position);
        holder.textView.setText(favorecido.getNome());
        holder.favorecido = favorecido;
    }

    @Override
    public int getItemCount() {
        return favorecidos.size();
    }

    public void setFavorecidos(List<Favorecido> favorecidos) {
        this.favorecidos = favorecidos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private Favorecido favorecido;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, FavorecidoActivity.class);
                    i.putExtra(Favorecido.class.getSimpleName(), favorecido);
                    i.putExtra("operacao", "alterar");
                    fragment.startActivityForResult(i, 0);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    controller.delete(favorecido);
                    favorecidos.remove(favorecido);
                    notifyDataSetChanged();
                    return true;
                }
            });
        }
    }
}
