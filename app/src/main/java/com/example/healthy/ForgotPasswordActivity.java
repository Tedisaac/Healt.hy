package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {
EditText email6;
Button next,close;
FloatingActionButton back3;
TextView switchaccouts,emailTV;
ProgressDialog progressDialog;
Dialog dialog;
LottieAnimationView emailsent;
    String emailReset;

//Firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        dialog = new Dialog(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");



        email6 = findViewById(R.id.email6);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailReset = email6.getText().toString().trim();
                if (email6.length() == 0){
                    email6.setError("Input Field Required");

                }else {
                    email6.getText().clear();
                    progressDialog.setMessage("sending...");
                    progressDialog.show();
                        firebaseAuth.sendPasswordResetEmail(emailReset).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.dismiss();
                                openDialogBox();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ForgotPasswordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });



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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ForgotPasswordActivity.this, SignIn.class));
        finish();
    }

    private void openDialogBox() {
        dialog.setContentView(R.layout.custom_email_dialog_box);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        emailTV = dialog.findViewById(R.id.actual_email);
        emailTV.setText(emailReset);
        close = dialog.findViewById(R.id.btn_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(ForgotPasswordActivity.this,SignIn.class));
                finish();
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