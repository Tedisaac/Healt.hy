package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
FloatingActionButton fab;
EditText uname,email1,pass1,cpass;
CheckBox checkBox;
Button signup2;

private FirebaseAuth firebaseAuth;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();

        fab = findViewById(R.id.back_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LoginActivity.class));
                finish();
            }
        });
        uname = findViewById(R.id.username);
        email1 = findViewById(R.id.email1);
        pass1 = findViewById(R.id.pass1);
        cpass = findViewById(R.id.cpass);
        checkBox = findViewById(R.id.showpassword2);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        signup2 = findViewById(R.id.sign_up2);
        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = uname.getText().toString().trim();
                String em1_string = email1.getText().toString().trim();
                String pass_string = pass1.getText().toString().trim();
                String cpass_string = cpass.getText().toString().trim();
                if (user_name.length() == 0) {
                    uname.setError("Input Field Required");
                } if (em1_string.length() == 0) {
                    email1.setError("Input Field Required");
                } if (!Patterns.EMAIL_ADDRESS.matcher(em1_string).matches()) {
                    email1.setError("Input a valid email");
                } if (pass_string.length() == 0) {
                    pass1.setError("Input Field Required");
                } if (!PASSWORD_PATTERN.matcher(pass_string).matches()){
                    pass1.setError("Password too weak");
                } if (cpass_string.length() == 0) {
                    cpass.setError("Input Field Required");
                }
                if (pass_string.equals(cpass_string)){
                    firebaseAuth.createUserWithEmailAndPassword(em1_string, pass_string)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),SignIn.class));
                                        Toast.makeText(SignUp.this,"Registration successful",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignUp.this,"Registration failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else{
                cpass.setError("Passwords do not match");
                }
            }
        });

    }
}