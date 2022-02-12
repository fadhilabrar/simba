package com.example.afinal.lomba.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.afinal.R;

public class ActivityEditLomba extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lomba);
        toolbar = findViewById(R.id.tbEditLomba);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Lomba");
    }
}