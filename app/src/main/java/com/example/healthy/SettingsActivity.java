package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {
    FloatingActionButton settingsfab;
    RelativeLayout changeUsername,changePassword,deleteAccount;
    AlertDialog.Builder builder;
    ProgressDialog dialog;
    Dialog deleteDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsfab = findViewById(R.id.back_fab_settings);
        settingsfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                finish();
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        reference = database.getReference("Users");
        deleteDialog = new Dialog(this);
        dialog = new ProgressDialog(this);
        builder = new AlertDialog.Builder(this);
        changeUsername = findViewById(R.id.change_username_layout);
        changePassword = findViewById(R.id.change_password_layout);
        deleteAccount = findViewById(R.id.delete_account_layout);
        changeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View view2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.change_name,null);
                EditText newName = view2.findViewById(R.id.new_name_ed);
                Button updateName = view2.findViewById(R.id.update_name);
                builder.setView(view2);
                AlertDialog nameDialog = builder.create();
                nameDialog.show();
                updateName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.setMessage("Updating Username");
                        dialog.show();
                        String nameString = newName.getText().toString().trim();
                        if (TextUtils.isEmpty(nameString)){
                            newName.setError("Please input name");
                        }
                        if (firebaseUser != null){
                            if (firebaseUser.getDisplayName() != null){
                                //Toast.makeText(SettingsActivity.this, firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();
                                UserProfileChangeRequest name = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nameString)
                                        .build();
                                firebaseUser.updateProfile(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            dialog.dismiss();
                                            nameDialog.dismiss();
                                            HashMap<String,String> nameChange = new HashMap<>();
                                            nameChange.put("Username",nameString);
                                            reference.child("Patients").child(firebaseUser.getUid()).setValue(nameChange);
                                            //Toast.makeText(SettingsActivity.this, "Profile Name Updated", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SettingsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                           /* if(firebaseUser.getDisplayName() == null){
                                UserProfileChangeRequest name1 = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nameString)
                                        .build();
                                firebaseUser.updateProfile(name1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            dialog.dismiss();
                                            nameDialog.dismiss();
                                            Toast.makeText(SettingsActivity.this, "updated", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                HashMap<String,String> nameChange = new HashMap<>();
                                nameChange.put("Username",nameString);
                                reference.child("Patients").child(firebaseUser.getUid()).setValue(nameChange);
                                dialog.dismiss();
                                nameDialog.dismiss();

                            }*/
                        }

                    }
                });

            }
        });
       changePassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.change_password,null);
                EditText currentPassword = view1.findViewById(R.id.current_password_ed);
                EditText newPassword = view1.findViewById(R.id.new_password_ed);
                CheckBox currCB = view1.findViewById(R.id.show_current_password);
                CheckBox newCB = view1.findViewById(R.id.show_new_password);
                currCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            currentPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        } else {
                            currentPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                    }
                });
                newCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        } else {
                            newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                    }
                });
                Button update = view1.findViewById(R.id.update_password);
                builder.setView(view1);
                AlertDialog passDialog = builder.create();
                passDialog.show();
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String currentPassString = currentPassword.getText().toString().trim();
                        String newPassString = newPassword.getText().toString().trim();
                        if (TextUtils.isEmpty(currentPassString)){
                            currentPassword.setError("Input current password");
                            return;
                        }
                        if (TextUtils.isEmpty(newPassString)){
                            newPassword.setError("input new password");
                            return;
                        }
                        passDialog.dismiss();
                        updatePassword(currentPassString,newPassString);
                    }
                });

           }
       });
       deleteAccount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               deleteDialogBox();

           }
       });
    }

    private void deleteDialogBox() {
        deleteDialog.setContentView(R.layout.delete_account);
        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button yes = deleteDialog.findViewById(R.id.btn_yes);
        Button no = deleteDialog.findViewById(R.id.btn_no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Deleting Account...");
                dialog.show();
                reference.child("Patients").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists());
                        String passString = snapshot.child("Password").getValue(String.class);
                        AuthCredential credential = EmailAuthProvider.getCredential(firebaseUser.getEmail(),passString);
                        firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog.dismiss();
            }
        });
                deleteDialog.show();
    }

    private void updatePassword(String currentPassString, String newPassString) {
        dialog.setMessage("Updating password...");
        dialog.show();

        AuthCredential authCredential = EmailAuthProvider.getCredential(firebaseUser.getEmail(),currentPassString);
        firebaseUser.reauthenticate(authCredential)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        firebaseUser.updatePassword(newPassString)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialog.dismiss();
                                        Toast.makeText(SettingsActivity.this, "Password updated", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                dialog.dismiss();
                                Toast.makeText(SettingsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(SettingsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
        finish();
    }
}