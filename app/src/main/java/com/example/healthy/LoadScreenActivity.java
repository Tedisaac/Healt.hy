package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class LoadScreenActivity extends AppCompatActivity {
LottieAnimationView loadcircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);

        loadcircle = findViewById(R.id.loading_circle);
        loadcircle.playAnimation();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadScreenActivity.this,ConversationActivity.class));
                finish();
            }
        },3000);
    }
}