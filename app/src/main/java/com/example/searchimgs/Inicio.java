package com.example.searchimgs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class Inicio extends AppCompatActivity {
    private ImageView appName,spash,logo;
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        spash = findViewById(R.id.img);
        logo = findViewById(R.id.logo);
        lottieAnimationView = findViewById(R.id.lottie);
        spash.animate().translationY(-5600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(-5600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-5600).setDuration(1000).setStartDelay(4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Inicio.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },4500);
    }
}