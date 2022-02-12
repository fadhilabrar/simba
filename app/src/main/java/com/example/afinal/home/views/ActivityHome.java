package com.example.afinal.home.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.afinal.R;
import com.example.afinal.artikel.views.ActivityArtikel;
import com.example.afinal.home.adapter.RecyclerMenuAdapter;
import com.example.afinal.home.model.Menu;
import com.example.afinal.lomba.views.ActivityCreateLomba;
import com.example.afinal.lomba.views.ActivityLomba;
import com.example.afinal.profil.views.ActivityProfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityHome extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    RecyclerView rv_menu, rv_lomba;
    RecyclerMenuAdapter menuAdapter;
    List<Menu> menuList;
    String[] carouselImg = {"images.unsplash.com/photo-1531256379416-9f000e90aacc?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1567&q=80",
    "https://images.unsplash.com/photo-1506765515384-028b60a970df?ixid=MXwxMjA3fDB8MHxzZWFyY2h8Mnx8YmFubmVyfGVufDB8fDB8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
    "https://images.unsplash.com/photo-1582292832746-36537c86a776?ixid=MXwxMjA3fDB8MHxzZWFyY2h8N3x8YmFubmVyfGVufDB8fDB8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"};
    CarouselView carouselView;
    BottomNavigationView navigationView;
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(carouselImg[position]).into(imageView);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menuList = new ArrayList<>();
        getdata();
        rv_menu = findViewById(R.id.rv_menu);
        carouselView = findViewById(R.id.carouselView);
        navigationView = findViewById(R.id.navigation);
        menuAdapter = new RecyclerMenuAdapter(menuList,this);

        carouselView.setPageCount(carouselImg.length);
        carouselView.setImageListener(imageListener);

        rv_menu.setAdapter(menuAdapter);
        rv_menu.setHasFixedSize(true);
        rv_menu.setLayoutManager(new GridLayoutManager(this,3));
        rv_menu.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        navigationView.setSelectedItemId(R.id.menuHome);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        break;
                    case R.id.menuLomba:
                        startActivity(new Intent(ActivityHome.this, ActivityLomba.class));
                        break;
                    case R.id.menuArtikel:
                        startActivity(new Intent(ActivityHome.this, ActivityArtikel.class));
                        break;
                    case R.id.menuProfil:
                        startActivity(new Intent(ActivityHome.this, ActivityProfil.class));
                        break;
                    case R.id.menuTambah:
                        startActivity(new Intent(ActivityHome.this, ActivityCreateLomba.class));
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Sekali Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        navigationView.setSelectedItemId(R.id.menuHome);
        super.onResume();
    }

    private void getdata() {
        menuList.add(new Menu(R.drawable.gamepad,"E-Sports"));
        menuList.add(new Menu(R.drawable.multimedia,"Cinematography"));
        menuList.add(new Menu(R.drawable.gamepad,"E-Sports"));
        menuList.add(new Menu(R.drawable.multimedia,"Cinematography"));
        menuList.add(new Menu(R.drawable.gamepad,"E-Sports"));
        menuList.add(new Menu(R.drawable.multimedia,"Cinematography"));
    }


}