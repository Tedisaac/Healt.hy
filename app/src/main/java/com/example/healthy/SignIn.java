package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    FloatingActionButton fab2;
    EditText email,password11;
    CheckBox checkBox;
    Button signin2;
    TextView forgotpassword;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //getSupportActionBar().hide();

        fab2 = findViewById(R.id.back2_fab);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, LoginActivity.class));
                finish();
            }
        });
        email = findViewById(R.id.email);
        password11 = findViewById(R.id.pass);
        checkBox = findViewById(R.id.showpassword);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password11.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password11.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        signin2 = findViewById(R.id.sign_in2);
        signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_string = email.getText().toString().trim();
                String password_string = password11.getText().toString().trim();
                if (email_string.length() == 0){
                    email.setError("Input Field required");
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()){
                    email.setError("Input a valid email");
                } else if (password_string.length() == 0){
                    password11.setError("Input Field required");
                } else {
                    Toast.makeText(SignIn.this, "Successful", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(SignIn.this, MainActivity.class));
                    finish();
                }

            }
        });
        forgotpassword = findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, ForgotPasswordActivity.class));
                finish();
            }
        });
    }

}