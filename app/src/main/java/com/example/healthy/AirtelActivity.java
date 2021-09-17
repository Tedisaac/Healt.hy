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

public class AirtelActivity extends AppCompatActivity {

    Button airtelPay,airtelCancel;
    Dialog paymentDialog;
    Button ok;
    LottieAnimationView payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airtel);

        airtelPay = findViewById(R.id.airtel_pay_btn);
        airtelCancel = findViewById(R.id.cancel_airtel_pay_btn);
        airtelPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentDialogBox();
            }
        });
        airtelCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AirtelActivity.this,PaymentActivity.class));
                finish();
            }
        });
        paymentDialog = new Dialog(this);
    }
    private void paymentDialogBox(){
        paymentDialog.setContentView(R.layout.payment_successful_dialog_box);
        paymentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ok = paymentDialog.findViewById(R.id.payment_ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentDialog.dismiss();
                startActivity(new Intent(AirtelActivity.this, LoadScreenActivity.class));
                finish();
            }
        });
        payment = paymentDialog.findViewById(R.id.payment_lottie);
        payment.playAnimation();
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment.playAnimation();
            }
        });
        paymentDialog.show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AirtelActivity.this,PaymentActivity.class));
        finish();
    }
}