package com.example.afinal.lomba.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.afinal.R;
import com.squareup.picasso.Picasso;

public class ActivityDetailLomba extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtNama, txtPenyelenggara, txtTema, txtLokasi, txtHadiah, txtTanggal, txtDeskripsi;
    ImageView imgLomba;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lomba);
        txtNama = findViewById(R.id.txtDetailLombaNama);
        txtDeskripsi = findViewById(R.id.txtDetailLombaDeskripsi);
        txtPenyelenggara = findViewById(R.id.txtDetailLombaPenyelenggara);
        txtHadiah = findViewById(R.id.txtDetailLombaHadiah);
        txtLokasi = findViewById(R.id.txtDetailLombaLokasi);
        txtTanggal = findViewById(R.id.txtDetailLombaTgl);
        txtTema = findViewById(R.id.txtDetailLombaTema);
        imgLomba = findViewById(R.id.imgDetailLomba);
        toolbar = findViewById(R.id.tbDetailLomba);

        toolbar.setTitle("Detail Lomba");
        
        setSupportActionBar(toolbar);

        bundle= getIntent().getExtras();
        txtNama.setText(bundle.getString("nama"));
        txtPenyelenggara.setText(bundle.getString("penyelenggara"));
        txtTema.setText(bundle.getString("tema"));
        txtLokasi.setText(bundle.getString("lokasi"));
        txtHadiah.setText(bundle.getString("hadiah"));
        txtTanggal.setText(bundle.getString("tanggal"));
        txtDeskripsi.setText(bundle.getString("deskripsi"));
        Picasso.get().load(this.getString(R.string.img_folder)+bundle.getString("img")).into(imgLomba);
    }
}