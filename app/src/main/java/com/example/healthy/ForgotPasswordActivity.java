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
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ForgotPasswordActivity extends AppCompatActivity {
EditText email6;
Button next,close;
FloatingActionButton back3;
TextView switchaccouts;
Dialog dialog;
LottieAnimationView emailsent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //getSupportActionBar().hide();


        dialog = new Dialog(this);


        email6 = findViewById(R.id.email6);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email6.length() == 0){
                    email6.setError("Input Field Required");

                }else {
                    openDialogBox();
                }

            }
        });
        back3 = findViewById(R.id.back3_fab);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this, SignIn.class));
                finish();
            }
        });

        switchaccouts = findViewById(R.id.switchaccounts);
        switchaccouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this, SignIn.class));
                finish();
            }
        });

    }

    private void openDialogBox() {
        dialog.setContentView(R.layout.custom_email_dialog_box);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        close = dialog.findViewById(R.id.btn_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        emailsent = dialog.findViewById(R.id.reset_email);
        emailsent.playAnimation();
        emailsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailsent.playAnimation();
            }
        });
        dialog.show();

    }

}