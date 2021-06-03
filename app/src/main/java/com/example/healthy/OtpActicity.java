package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.chaos.view.PinView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OtpActicity extends AppCompatActivity {
LottieAnimationView otp_view;
FloatingActionButton otp_back_btn;
Button verify_btn;
PinView pinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_acticity);

        pinView = findViewById(R.id.pinview);
        otp_view = findViewById(R.id.otp_gif);
        otp_view.playAnimation();
        otp_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp_view.playAnimation();
            }
        });
        otp_back_btn = findViewById(R.id.otp_fab);
        otp_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtpActicity.this, PhoneActivity.class));
                finish();
            }
        });
        verify_btn = findViewById(R.id.verify);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinView.length() == 0){
                    Toast.makeText(OtpActicity.this, "Please input number", Toast.LENGTH_SHORT).show();
                }else if(pinView.length() < 6){
                    Toast.makeText(OtpActicity.this, "Please input complete number", Toast.LENGTH_SHORT).show();
                }
                else{
                startActivity(new Intent(OtpActicity.this, OtpLoadScreenActivity.class));
                finish();
                }
            }
        });
    }
}