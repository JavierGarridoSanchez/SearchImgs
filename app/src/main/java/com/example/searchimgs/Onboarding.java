package com.example.searchimgs;

import static com.example.searchimgs.R.string.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class Onboarding extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmento1();
        fragmento2();
    }

    private void fragmento1() {
        addFragment(new Step.Builder()
                .setTitle(getString(title1))
                .setContent(getString(R.string.terminos))
                .setSummary(getString(siguiente))
                .setBackgroundColor(Color.parseColor("#00bcd4"))
                .setDrawable(R.drawable.logo2).build());
    }

    private void fragmento2() {
        addFragment(new Step.Builder()
                .setTitle(getString(title2))
                .setContent(getString(R.string.terminos2))
                .setSummary(getString(siguiente))
                .setBackgroundColor(Color.parseColor("#B8B824"))
                .setDrawable(R.drawable.logo3).build());
    }

    @Override
    public void finishTutorial(){
        Intent i = new Intent(this, VistaListActivity.class);
        startActivity(i);
    }

    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}