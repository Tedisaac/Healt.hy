package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class OtpLoadScreenActivity extends AppCompatActivity {
LottieAnimationView otploadcircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_load_screen);

        otploadcircle = findViewById(R.id.otp_loading_circle);
        otploadcircle.playAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(OtpLoadScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}