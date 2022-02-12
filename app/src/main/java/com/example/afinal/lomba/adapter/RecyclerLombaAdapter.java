package com.example.afinal.lomba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.lomba.model.Kategori;
import com.example.afinal.lomba.model.Lomba;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerLombaAdapter extends RecyclerView.Adapter<RecyclerLombaAdapter.ViewHolder>{
    private List<Lomba> list;
    private LayoutInflater inflater;
    private Context context;

    public RecyclerLombaAdapter(List<Lomba> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerLombaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lomba, parent, false);
        return new RecyclerLombaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerLombaAdapter.ViewHolder holder, int position) {
        holder.txtNama.setText(list.get(position).getNama());
        holder.txtTanggal.setText(list.get(position).getTanggal());
        holder.txtPenyelenggara.setText(list.get(position).getPenyelenggara());
        String url = context.getString(R.string.img_folder)+list.get(position).getImg();
        Picasso.get().load(url).into(holder.imgLomba);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLomba;
        TextView txtNama, txtPenyelenggara, txtTanggal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLomba = itemView.findViewById(R.id.imgListLomba);
            txtNama = itemView.findViewById(R.id.txtNamaLomba);
            txtPenyelenggara = itemView.findViewById(R.id.txtPenyelenggaraLomba);
            txtTanggal = itemView.findViewById(R.id.txtTglLomba);
        }
    }
}
