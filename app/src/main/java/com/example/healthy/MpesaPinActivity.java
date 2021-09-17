package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;

public class MpesaPinActivity extends AppCompatActivity {

    EditText mPesaPin;
    Button mPesaCancel,mPesaOk,ok;
    Dialog paymentDialog;
    LottieAnimationView payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa_pin);
        mPesaPin =findViewById(R.id.mpesa_pin_edit_text);
        mPesaCancel = findViewById(R.id.mpesa_pin_cancel);
        mPesaOk = findViewById(R.id.mpesa_pin_ok);
        mPesaCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MpesaPinActivity.this,PaymentActivity.class));
                finish();
            }
        });
        mPesaOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentDialogBox();
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
                startActivity(new Intent(MpesaPinActivity.this, LoadScreenActivity.class));
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

}