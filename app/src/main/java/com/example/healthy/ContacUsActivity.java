package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.StringSearch;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class ContacUsActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    Button addDetails;
    EditText bloodGroup,height,weight;
    TextView genderTV,birthDateTV,bloodGroupTV,heightTV,weightTV;
    TextView date;
    //DatePickerDialog.OnDateSetListener setListener;
    FloatingActionButton medicBack;
    //fireBase
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    String userId = firebaseUser.getUid();;
    ProgressDialog submitDialog;

    //Strings
    //String gender;
    String birthDate;
    String blood;
    String uHeight;
    String userHeight;
    String uWeight;
    String userWeight;
    String genderString;
    String birthString;
    String bloodString;
    String heightString;
    String weightString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contac_us);

        submitDialog = new ProgressDialog(this);


        fetchData();

        genderTV = findViewById(R.id.gender_tv);
        birthDateTV = findViewById(R.id.birth_date_tv);
        bloodGroupTV = findViewById(R.id.blood_group_tv);
        heightTV = findViewById(R.id.height_tv);
        weightTV = findViewById(R.id.weight_tv);

        medicBack = findViewById(R.id.back_fab_medical);
        medicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContacUsActivity.this,MainActivity.class));
                finish();
            }
        });
        radioGroup = findViewById(R.id.radio_group);
        date = findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ContacUsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i+"/"+i1+"/"+i2);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        bloodGroup = findViewById(R.id.blood_group);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        addDetails = findViewById(R.id.add_details);
        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //radioButtonDetails
                int radioId = radioGroup.getCheckedRadioButtonId();
                birthDate = date.getText().toString();
                blood = bloodGroup.getText().toString();
                uHeight = height.getText().toString();
                userHeight = uHeight+" inches";
                uWeight = weight.getText().toString();
                userWeight = uWeight+" kg";
                if ( radioId == -1){
                    Toast.makeText(ContacUsActivity.this, "Please select a gender", Toast.LENGTH_SHORT).show();

                } else{
                    if (date.length() == 0){
                        date.setError("Please input Birthdate ");
                    }
                    if (blood.length() == 0){
                        bloodGroup.setError("Please input Bloodgroup");
                    }
                    if (height.length() == 0){
                        height.setError("Please input Height");
                    }
                    if (weight.length() == 0){
                        weight.setError("Please input Weight");
                    }
                    radioButton = findViewById(radioId);
                    submitDialog.setMessage("Submitting data...");
                    submitDialog.show();
                    submitMedicalData();



                }
            }
        });

    }
    private void submitMedicalData() {
        if (firebaseUser != null){
            HashMap<String, String> medicalData = new HashMap<>();
            medicalData.put("Gender", radioButton.getText().toString());
            medicalData.put("Bithdate", birthDate);
            medicalData.put("Bloodgroup", blood);
            medicalData.put("Height", userHeight);
            medicalData.put("Weight", userWeight);
            root.child("Patients").child(userId).child("MedicalInfo").setValue(medicalData);
            submitDialog.dismiss();
            Toast.makeText(ContacUsActivity.this, "Data submitted", Toast.LENGTH_SHORT).show();
        }
    }
    private void fetchData() {
        root.child("Patients").child(userId).child("MedicalInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                genderString = snapshot.child("Gender").getValue(String.class);
                birthString = snapshot.child("Bithdate").getValue(String.class);
                bloodString = snapshot.child("Bloodgroup").getValue(String.class);
                heightString = snapshot.child("Height").getValue(String.class);
                weightString = snapshot.child("Weight").getValue(String.class);
                displayData();
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void displayData() {
        genderTV.setText(genderString);
        birthDateTV.setText(birthString);
        bloodGroupTV.setText(bloodString);
        heightTV.setText(heightString);
        weightTV.setText(weightString);
    }
}