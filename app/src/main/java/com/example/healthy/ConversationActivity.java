package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConversationActivity extends AppCompatActivity {

    EditText userinput;
    RecyclerView Conversation;
    MessageAdapter messageAdapter;
    List<RespnseMessage> respnseMessages;
    ImageView sendImage;
    FloatingActionButton convo_back_fab;
    final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
    String timeStamp = dateFormat.format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_conversation);
        userinput = findViewById(R.id.userInput);
        Conversation = findViewById(R.id.conversation);
        sendImage = findViewById(R.id.sendMessage);
        respnseMessages =  new ArrayList<>();
        messageAdapter = new MessageAdapter(respnseMessages);
        Conversation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        Conversation.setAdapter(messageAdapter);
        send();
        convo_back_fab = findViewById(R.id.convo_back);
        convo_back_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConversationActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void send() {
        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RespnseMessage message = new RespnseMessage(userinput.getText().toString(),true,timeStamp);
                respnseMessages.add(message);
                RespnseMessage message1 = new RespnseMessage(userinput.getText().toString(),false,"12:44");
                respnseMessages.add(message1);
                messageAdapter.notifyDataSetChanged();
                userinput.getText().clear();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ConversationActivity.this, MainActivity.class));
        finish();
    }
}