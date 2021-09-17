package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtpActicity extends AppCompatActivity {
LottieAnimationView otp_view;
FloatingActionButton otp_back_btn;
Button verify_btn;
TextView resendCode;
PinView pinView;

ProgressDialog otpDailog;


private FirebaseAuth firebaseAuth;
private PhoneAuthProvider.ForceResendingToken forceResendingToken;
private String OTP;
private ProgressDialog pd;
    public String backendotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_acticity);

        firebaseAuth = FirebaseAuth.getInstance();
        otpDailog = new ProgressDialog(this);

        pinView = findViewById(R.id.pinview);
        backendotp = getIntent().getStringExtra("backendotp");

        resendCode = findViewById(R.id.resend_code);
        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
                otpDailog.setMessage("Verifying OTP");
                otpDailog.show();
                String ver_code = pinView.getText().toString();
                if (pinView.length() == 0){
                    Toast.makeText(OtpActicity.this, "Please input number", Toast.LENGTH_SHORT).show();
                }else if(pinView.length() < 6){
                    Toast.makeText(OtpActicity.this, "Please input complete number", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (backendotp!=null){
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(backendotp,ver_code);
                        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    otpDailog.dismiss();
                                    startActivity(new Intent(OtpActicity.this,MainActivity.class));
                                    finish();
                                }else {
                                    Toast.makeText(OtpActicity.this, "Enter the Correct OTP", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }else {
                        Toast.makeText(OtpActicity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(OtpActicity.this, PhoneActivity.class));
        finish();
    }
}