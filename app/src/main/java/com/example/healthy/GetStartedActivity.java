package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class GetStartedActivity extends AppCompatActivity {
LottieAnimationView pulse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        pulse = findViewById(R.id.getting_started_loading_circle);
        pulse.playAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(GetStartedActivity.this,LoginActivity.class));
                finish();
            }
        },1000);
    }
}