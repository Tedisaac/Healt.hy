package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PhoneActivity extends AppCompatActivity {
    FloatingActionButton back_phone;
    LottieAnimationView phone_lottie;
    EditText pnumber;
    Button get_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        back_phone = findViewById(R.id.phone_fab);
        back_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneActivity.this, LoginActivity.class));
                finish();
            }
        });
        phone_lottie = findViewById(R.id.phone_gif);
        phone_lottie.playAnimation();
        phone_lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_lottie.playAnimation();
            }
        });
        pnumber = findViewById(R.id.phone_number);
        get_otp = findViewById(R.id.phone_number_btn);
        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_number = pnumber.getEditableText().toString().trim();
                if (p_number.length() == 0) {
                    pnumber.setError("Input Phone Number");
                }else if (p_number.length() < 9){
                    pnumber.setError("Input complete number");
                }
                else {
                    startActivity(new Intent(PhoneActivity.this, OtpActicity.class));
                    finish();
                }
            }
        });
    }
}