package com.example.afinal.artikel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.artikel.model.Artikel;
import com.example.afinal.lomba.adapter.RecyclerLombaAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerArtikelAdapter extends RecyclerView.Adapter<RecyclerArtikelAdapter.ViewHolder> {
    List<Artikel> list;
    LayoutInflater inflater;

    public RecyclerArtikelAdapter(List<Artikel> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_artikel, parent, false);
        return new RecyclerArtikelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtJudul.setText(list.get(position).getJudul());
        holder.txtTanggal.setText(list.get(position).getCreated_at());
        Picasso.get().load(list.get(position).getImg()).into(holder.imgArtikel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgArtikel;
        TextView txtJudul,txtTanggal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArtikel = itemView.findViewById(R.id.imgListArtikel);
            txtJudul = itemView.findViewById(R.id.txtJudulArtikel);
            txtTanggal = itemView.findViewById(R.id.txtTglArtikel);
        }
    }
}
