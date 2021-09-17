package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    FloatingActionButton historyBack;
    RecyclerView historyRecyclerView;
    RecyclerView.Adapter historyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        historyBack = findViewById(R.id.back_fab_history);
        historyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                finish();
            }
        });

        historyRecyclerView = findViewById(R.id.hist_recycler_view);
        historyRecycler();

    }

    private void historyRecycler() {
        historyRecyclerView.setHasFixedSize(true);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ArrayList<SymptomListGetterSetter> histInfo = new ArrayList<>();
        histInfo.add(new SymptomListGetterSetter("Headaches,Back Pains,Fatigue","Malaria.Take bascopan(2x1)","02","May","Symptom","Remedy"));
        histInfo.add(new SymptomListGetterSetter("Fatigue","Cholera.Take cetrizine(1x1)","01","June","Symptom","Remedy"));
        histInfo.add(new SymptomListGetterSetter("Muscle Aches","Tuberculosis.Take tubercine(1x1)","03","Aug","Symptom","Remedy"));
        histInfo.add(new SymptomListGetterSetter("Stress","Depression.Take Antidepressants(3x1)","01","Sept","Symptom","Remedy"));
        histInfo.add(new SymptomListGetterSetter("Stress","Depression.Take Antidepressants(3x1)","01","Sept","Symptom","Remedy"));

        historyAdapter = new SymptomListAdapter(histInfo);
        historyRecyclerView.setAdapter(historyAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
        finish();
    }
}