package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {
    FloatingActionButton back_phone;
    LottieAnimationView phone_lottie;
    EditText pnumber;
    Button get_otp;

    public String phone_number;

    ProgressDialog phoneDialog;

    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    private ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        phoneDialog = new ProgressDialog(this);

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


        auth = FirebaseAuth.getInstance();

        pd =  new ProgressDialog(this);
        pd.setTitle("Please Wait...");
        pd.setCanceledOnTouchOutside(false);



        get_otp = findViewById(R.id.phone_number_btn);
        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneDialog.setMessage("Sending OTP");
                phoneDialog.show();
                String p_number = pnumber.getEditableText().toString().trim();
                phone_number = "+254"+p_number;
                if (p_number.length() == 0) {
                    pnumber.setError("Input Phone Number");
                } else if (p_number.length() < 9) {
                    pnumber.setError("Input complete number");
                } else {
                    PhoneAuthProvider.getInstance()
                            .verifyPhoneNumber(phone_number, 60, TimeUnit.SECONDS, PhoneActivity.this,
                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {

                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            phoneDialog.dismiss();
                                            Intent intent = new Intent(PhoneActivity.this,OtpActicity.class);
                                            intent.putExtra("mobile",phone_number);
                                            intent.putExtra("backendotp",backendotp);
                                            startActivity(intent);
                                        }
                                    });
                   /* Intent intent = new Intent(PhoneActivity.this,OtpActicity.class);
                    intent.putExtra("mobile",phone_number);
                    startActivity(intent);*/

                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PhoneActivity.this, LoginActivity.class));
        finish();
    }
}