package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaymentActivity extends AppCompatActivity {

    FloatingActionButton back_payment;
    ImageView mpesa, airtel, visa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        back_payment = findViewById(R.id.back_fab_payment);
        back_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this,MainActivity.class));
                finish();
            }
        });
        mpesa = findViewById(R.id.mpesa_id);
        airtel = findViewById(R.id.airtel_id);
        //visa =  findViewById(R.id.visa_id);
        mpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(PaymentActivity.this,MpesaActivity.class));
            finish();
            }
        });
        airtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(PaymentActivity.this,AirtelActivity.class));
            finish();
            }
        });
       /* visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(PaymentActivity.this,VisaActivity.class));
            finish();
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaymentActivity.this,MainActivity.class));
        finish();
    }
}