package com.example.afinal.artikel.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.afinal.R;
import com.example.afinal.api.ApiService;
import com.example.afinal.api.ApiUtils;
import com.example.afinal.artikel.adapter.RecyclerArtikelAdapter;
import com.example.afinal.artikel.model.Artikel;
import com.example.afinal.helpers.BaseResponse;
import com.example.afinal.helpers.RecyclerItemClickListener;
import com.example.afinal.home.views.ActivityHome;
import com.example.afinal.lomba.model.Lomba;
import com.example.afinal.lomba.views.ActivityCreateLomba;
import com.example.afinal.lomba.views.ActivityLomba;
import com.example.afinal.profil.views.ActivityProfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityArtikel extends AppCompatActivity {
    BottomNavigationView navigationView;
    RecyclerView rv_artikel;
    RecyclerArtikelAdapter artikelAdapter;
    ApiService apiService = ApiUtils.getAPIService();
    Context mContext = this;
    List<Artikel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        rv_artikel = findViewById(R.id.rv_artikel);
        getData();

        navigationView = findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.menuArtikel);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(ActivityArtikel.this, ActivityHome.class));
                        break;
                    case R.id.menuLomba:
                        startActivity(new Intent(ActivityArtikel.this, ActivityLomba.class));
                        break;
                    case R.id.menuArtikel:
                        break;
                    case R.id.menuProfil:
                        startActivity(new Intent(ActivityArtikel.this, ActivityProfil.class));
                        break;
                    case R.id.menuTambah:
                        startActivity(new Intent(ActivityArtikel.this, ActivityCreateLomba.class));
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        getData();
        navigationView.setSelectedItemId(R.id.menuArtikel);
        super.onResume();
    }

    @Override
    protected void onRestart() {
        getData();
        super.onRestart();
    }

    void getData(){
        apiService.getArtikel().enqueue(new Callback<BaseResponse<List<Artikel>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Artikel>>> call, Response<BaseResponse<List<Artikel>>> response) {
                if (response.isSuccessful()){
                    list = response.body().getData();
                    artikelAdapter = new RecyclerArtikelAdapter(list, mContext);
                    rv_artikel.setAdapter(artikelAdapter);
                    rv_artikel.setLayoutManager(new LinearLayoutManager(mContext));
                    rv_artikel.addOnItemTouchListener(
                            new RecyclerItemClickListener(mContext, rv_artikel ,new RecyclerItemClickListener.OnItemClickListener() {
                                @Override public void onItemClick(View view, int position) {

                                }

                                @Override public void onLongItemClick(View view, int position) {
                                    // do whatever
                                }
                            })
                    );;
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Artikel>>> call, Throwable t) {

            }
        });
    }
}