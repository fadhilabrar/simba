package com.example.afinal.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.home.model.Menu;

import java.util.List;

public class RecyclerMenuAdapter extends RecyclerView.Adapter<RecyclerMenuAdapter.ViewHolder> {
    private List<Menu> menuList;
    private Context ct;

    public RecyclerMenuAdapter(List<Menu> menuList, Context ct) {
        this.menuList = menuList;
        this.ct = ct;
    }

    @NonNull
    @Override
    public RecyclerMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_menu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMenuAdapter.ViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        holder.imageView.setImageDrawable(ct.getDrawable(menu.getImage()));
        holder.textView.setText(menu.getText());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.myimage);
            textView = itemView.findViewById(R.id.txtCard);
        }
    }
}
