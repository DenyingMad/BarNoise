package com.devilpanda.barnoise.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.devilpanda.barnoise.InitDB;
import com.devilpanda.barnoise.R;

import java.lang.ref.WeakReference;

public class LoadingActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        preferences = getSharedPreferences("com.devilpanda.", MODE_PRIVATE);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences.getBoolean("first_run", true)){
            InitDB initDB = new InitDB(new WeakReference<Context>(this), progressBar);
            initDB.execute();
            preferences.edit().putBoolean("first_run", false).commit();
        } else{
            Intent intent = new Intent(LoadingActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}