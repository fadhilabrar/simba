package com.example.afinal.profil.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.afinal.R;
import com.example.afinal.artikel.views.ActivityArtikel;
import com.example.afinal.home.views.ActivityHome;
import com.example.afinal.login.activity.ActivityLogin;
import com.example.afinal.login.session.Session;
import com.example.afinal.lomba.views.ActivityCreateLomba;
import com.example.afinal.lomba.views.ActivityLomba;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityProfil extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView navigationView;
    Session session;
    TextView txtNama, txtEmail, txtPhone, txtAlamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.tbProfil);
        txtAlamat = findViewById(R.id.txtProfilAlamat);
        txtEmail = findViewById(R.id.txtProfilEmail);
        txtNama = findViewById(R.id.txtProfilNama);
        txtPhone = findViewById(R.id.txtProfilPhone);

        toolbar.setTitle("Profil");
        setSupportActionBar(toolbar);


        session = new Session(this);

        txtNama.setText(session.getNama());
        txtEmail.setText(session.getEmail());
        txtPhone.setText(session.getNo_telp());
        txtAlamat.setText(session.getAlamat());

        navigationView.setSelectedItemId(R.id.menuProfil);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(ActivityProfil.this, ActivityHome.class));
                        break;
                    case R.id.menuLomba:
                        startActivity(new Intent(ActivityProfil.this, ActivityLomba.class));
                        break;
                    case R.id.menuArtikel:
                        startActivity(new Intent(ActivityProfil.this, ActivityArtikel.class));
                        break;
                    case R.id.menuProfil:
                        break;
                    case R.id.menuTambah:
                        startActivity(new Intent(ActivityProfil.this, ActivityCreateLomba.class));
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logout){
            session.setLogin(false);
            startActivity(new Intent(ActivityProfil.this, ActivityLogin.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        navigationView.setSelectedItemId(R.id.menuProfil);
        super.onResume();
    }
}