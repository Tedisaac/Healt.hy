package com.example.healthy;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ConsultantActivity extends AppCompatActivity {
    RecyclerView con_recycler_view;
    RecyclerView.Adapter con_adapter;

    FloatingActionButton consultantBack;
    CardView consultCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant);

        consultantBack = findViewById(R.id.consultant_back_fab);
        consultantBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConsultantActivity.this,MainActivity.class));
                finish();
            }
        });

        con_recycler_view = findViewById(R.id.consultant_recycler_view);
        conRecyler();
    }

    private void conRecyler() {
        con_recycler_view.setHasFixedSize(true);
        con_recycler_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ArrayList<consultantHelper> conInfo = new ArrayList<>();
        conInfo.add(new consultantHelper(R.drawable.dentist,"Dentist"));
        conInfo.add(new consultantHelper(R.drawable.optician,"Optician"));
        conInfo.add(new consultantHelper(R.drawable.psychiatrist,"Psychiatrists"));
        conInfo.add(new consultantHelper(R.drawable.surgeon,"General Surgeons"));
       /* conInfo.add(new consultantHelper(R.drawable.hospital,"Hematologists"));
        conInfo.add(new consultantHelper(R.drawable.hospital,"Cardiologists"));*/

        con_adapter = new consultantInfo(conInfo,this);
        con_recycler_view.setAdapter(con_adapter);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ConsultantActivity.this,MainActivity.class));
        finish();
    }
}