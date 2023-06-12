package com.example.khabarkoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class searchFood extends AppCompatActivity {

    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        username = findViewById(R.id.username);
        Intent intent = getIntent();

        String str = intent.getStringExtra("message_key");

        username.setText(str);

    }
}