package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class MpesaActivity extends AppCompatActivity {

    Button mpesa_pay, mpesa_cancel, ok;
    LottieAnimationView payment;
    Dialog payment_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa);

        payment_dialog = new Dialog(this);

        mpesa_pay = findViewById(R.id.mpesa_pay_btn);
        mpesa_cancel = findViewById(R.id.cancel_mpesa_pay_btn);
        mpesa_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MpesaActivity.this,MpesaPinActivity.class));
                finish();
            }
        });
        mpesa_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MpesaActivity.this, PaymentActivity.class));
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MpesaActivity.this, PaymentActivity.class));
        finish();
    }
}