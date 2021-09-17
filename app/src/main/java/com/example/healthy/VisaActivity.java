package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VisaActivity extends AppCompatActivity {

    Button visaPay,visaCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa);
        /*visaPay =findViewById(R.id.visa_pay_btn);
        visaCancel =findViewById(R.id.cancel_visa_pay_btn);
        visaPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        visaCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisaActivity.this,PaymentActivity.class));
                finish();
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VisaActivity.this,PaymentActivity.class));
        finish();
    }
}