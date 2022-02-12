package com.example.afinal.lomba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.lomba.model.Kategori;

import java.util.List;

public class AdapterListKategori extends RecyclerView.Adapter<AdapterListKategori.ViewHolder> {
    private List<Kategori> list;
    private LayoutInflater inflater;

    public AdapterListKategori(List<Kategori> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterListKategori.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_kategori_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListKategori.ViewHolder holder, int position) {
        holder.txtKategori.setText(list.get(position).getNamaKategori());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
     TextView txtKategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKategori = itemView.findViewById(R.id.txtKategori);

        }
    }
}
