package com.example.turismoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.example.turismoapp.Database.DBHelper;
import com.example.turismoapp.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class SplashActivity extends Activity {
    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                verification();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(task, 3000);
    }

    private void verification() {
        String Passport = sharedPreferences.getString("passport", "");
        if (Passport.contains(".")) {
            goToMenuHotels();

        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void goToMenuHotels() {
        Intent intent = new Intent(SplashActivity.this, HotelsActivity.class);
        startActivity(intent);
        finish();
    }
}