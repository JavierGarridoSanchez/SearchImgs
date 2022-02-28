package com.example.searchimgs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lanzarInicio();
    }

    private void lanzarInicio() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        Boolean inicio = sharedPreferences.getBoolean("firstStartBoarding", false);
        if (!inicio) {
            sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstStartBoarding", true);
            editor.apply();
            Intent i = new Intent(this, Onboarding.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(this, VistaListActivity.class);
            startActivity(i);
        }
    }
}