package br.iesb.medanvisa;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RemedioViewHolder extends RecyclerView.ViewHolder {
    final TextView codigoBarra;
    final TextView principioAtivo;
    final TextView produto;
    final TextView precoFabrica;
    final TextView precoMercado;

    public RemedioViewHolder(View view) {
        super(view);
        codigoBarra = (TextView) view.findViewById(R.id.item_remedio_codigoBarra);
        principioAtivo = (TextView) view.findViewById(R.id.item_remedio_principio_ativo);
        produto = (TextView) view.findViewById(R.id.item_remedio_produto);
        precoFabrica = (TextView) view.findViewById(R.id.item_remedio_precoFabrica);
        precoMercado = (TextView) view.findViewById(R.id.item_remedio_precoMercado);
    }


}
