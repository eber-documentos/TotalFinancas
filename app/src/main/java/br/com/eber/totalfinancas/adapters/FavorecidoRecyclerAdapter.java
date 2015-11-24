package br.com.eber.totalfinancas.adapters;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.eber.totalfinancas.R;
import br.com.eber.totalfinancas.activities.FavorecidoActivity;
import br.com.eber.totalfinancas.enuns.Operacao;
import br.com.eber.totalfinancas.models.Favorecido;

public class FavorecidoRecyclerAdapter extends RecyclerView.Adapter<FavorecidoRecyclerAdapter.ViewHolder> {

    private Fragment fragment;
    private List<Favorecido> favorecidos;

    public FavorecidoRecyclerAdapter(Fragment fragment, List<Favorecido> favorecidos) {
        this.fragment = fragment;
        this.favorecidos = favorecidos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(fragment.getContext()).inflate(R.layout.list_row_favorecido, parent, false);
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

                    final Dialog dialog = new Dialog(fragment.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.options_dialog);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();

                    LinearLayout llAlterar = (LinearLayout) dialog.findViewById(R.id.llAlterar);
                    LinearLayout llExcluir = (LinearLayout) dialog.findViewById(R.id.llExcluir);
                    LinearLayout llVisualizar = (LinearLayout) dialog.findViewById(R.id.llVisualizar);

                    llAlterar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent i = new Intent(fragment.getContext(), FavorecidoActivity.class);
                            i.putExtra(FavorecidoActivity.ENTITY, favorecido);
                            i.putExtra(Operacao.asString(), Operacao.ALTERAR.getValue());
                            fragment.startActivityForResult(i, 0);
                        }
                    });

                    llExcluir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent i = new Intent(fragment.getContext(), FavorecidoActivity.class);
                            i.putExtra(FavorecidoActivity.ENTITY, favorecido);
                            i.putExtra(Operacao.asString(), Operacao.EXCLUIR.getValue());
                            fragment.startActivityForResult(i, 0);
                        }
                    });

                    llVisualizar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent i = new Intent(fragment.getContext(), FavorecidoActivity.class);
                            i.putExtra(FavorecidoActivity.ENTITY, favorecido);
                            i.putExtra(Operacao.asString(), Operacao.VISUALIZAR.getValue());
                            fragment.startActivityForResult(i, 0);
                        }
                    });
                }
            });
        }
    }
}
