package com.example.afinal.lomba.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.example.afinal.artikel.views.ActivityArtikel;
import com.example.afinal.helpers.BaseResponse;
import com.example.afinal.helpers.RecyclerItemClickListener;
import com.example.afinal.home.views.ActivityHome;
import com.example.afinal.lomba.adapter.RecyclerLombaAdapter;
import com.example.afinal.lomba.model.Lomba;
import com.example.afinal.profil.views.ActivityProfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLomba extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView navigationView;
    RecyclerView rv_lomba;
    ApiService apiService = ApiUtils.getAPIService();
    Context mContext = this;
    List<Lomba> list;
    RecyclerLombaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lomba);
        navigationView = findViewById(R.id.navigation);
        rv_lomba = findViewById(R.id.rv_lomba);
        getData();

        toolbar = findViewById(R.id.tbLomba);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daftar Lomba");

        navigationView.setSelectedItemId(R.id.menuLomba);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(ActivityLomba.this, ActivityHome.class));
                        break;
                    case R.id.menuLomba:
                        break;
                    case R.id.menuArtikel:
                        startActivity(new Intent(ActivityLomba.this, ActivityArtikel.class));
                        break;
                    case R.id.menuProfil:
                        startActivity(new Intent(ActivityLomba.this, ActivityProfil.class));
                        break;
                    case R.id.menuTambah:
                        startActivity(new Intent(ActivityLomba.this, ActivityCreateLomba.class));
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
        navigationView.setSelectedItemId(R.id.menuLomba);
        getData();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        getData();
        super.onRestart();
    }

    void getData(){
        apiService.getLomba().enqueue(new Callback<BaseResponse<List<Lomba>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Lomba>>> call, Response<BaseResponse<List<Lomba>>> response) {
                if (response.isSuccessful()){
                    list = response.body().getData();
                    adapter = new RecyclerLombaAdapter(list,mContext);
                    rv_lomba.setAdapter(adapter);
                    rv_lomba.setLayoutManager(new LinearLayoutManager(mContext));
                    rv_lomba.addOnItemTouchListener(
                            new RecyclerItemClickListener(mContext, rv_lomba ,new RecyclerItemClickListener.OnItemClickListener() {
                                @Override public void onItemClick(View view, int position) {
                                    Intent intent = new Intent(ActivityLomba.this, ActivityDetailLomba.class);
                                    intent.putExtra("nama",list.get(position).getNama());
                                    intent.putExtra("penyelenggara",list.get(position).getPenyelenggara());
                                    intent.putExtra("tanggal",list.get(position).getTanggal());
                                    intent.putExtra("lokasi",list.get(position).getLokasi());
                                    intent.putExtra("deskripsi",list.get(position).getDeskripsi());
                                    intent.putExtra("hadiah",list.get(position).getHadiah());
                                    intent.putExtra("img",list.get(position).getImg());
                                    intent.putExtra("tema",list.get(position).getTema());
                                    startActivity(intent);
                                }

                                @Override public void onLongItemClick(View view, int position) {
                                    // do whatever
                                }
                            })
                    );
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<List<Lomba>>> call, Throwable t) {

            }
        });
    }
}