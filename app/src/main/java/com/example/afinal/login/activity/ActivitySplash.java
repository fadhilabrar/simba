package com.example.afinal.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.afinal.MainActivity;
import com.example.afinal.home.views.ActivityHome;
import com.example.afinal.login.session.Session;

public class ActivitySplash extends AppCompatActivity {
    private Handler mWaitHandler = new Handler();
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new Session(this);

        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (session.getLogin()){
                        startActivity(new Intent(ActivitySplash.this, ActivityHome.class));
                    }else {
                        startActivity(new Intent(ActivitySplash.this, MainActivity.class));
                    }
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },5000);
    }

    @Override
    protected void onDestroy() {
        mWaitHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}