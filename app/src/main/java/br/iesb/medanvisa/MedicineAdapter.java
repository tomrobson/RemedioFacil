package br.iesb.medanvisa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by luand on 2017-09-14.
 */

public class MedicineAdapter extends RecyclerView.Adapter {

    private List<String> remedios;

    public MedicineAdapter() {
        this.remedios.add("Dipirona");
        this.remedios.add("Dipirona 1");
        this.remedios.add("Dipirona 2");
        this.remedios.add("Dipirona 3");
        this.remedios.add("Libera o TOM");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
