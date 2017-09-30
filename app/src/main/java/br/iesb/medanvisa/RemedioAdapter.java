package br.iesb.medanvisa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.iesb.medanvisa.model.Remedio;


class RemedioAdapter extends RecyclerView.Adapter {
    private List<Remedio> remedios;
    private Context context;

    public RemedioAdapter(List<Remedio> remedios, Context context) {
        this.remedios = remedios;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_remedio, parent, false);

        RemedioViewHolder holder = new RemedioViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RemedioViewHolder viewHolder = (RemedioViewHolder) holder;

        Remedio remedio = remedios.get(position);

        viewHolder.produto.setText(remedio.getProduto());
        viewHolder.codigoBarra.setText(remedio.getCodigoBarra());
        viewHolder.precoMercado.setText(remedio.getPrecoMercado().toString());
        viewHolder.precoFabrica.setText(remedio.getPrecoFabrica().toString());
        viewHolder.principioAtivo.setText(remedio.getPrincipioAtivo());
    }

    @Override
    public int getItemCount() {
        return remedios.size();
    }
}
