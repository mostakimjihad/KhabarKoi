package com.example.khabarkoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;

public class preference extends AppCompatActivity implements View.OnClickListener {

    Button kabab, kacchi, biriyani, beefRezela, burger, friedRice, next;
    String userStr;
    FirebaseDatabase db;
    DatabaseReference rf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        Intent intent = getIntent();
        userStr = intent.getStringExtra("message_key");

        kabab = findViewById(R.id.kabab);
        kacchi = findViewById(R.id.kacchi);
        biriyani = findViewById(R.id.biriyani);
        beefRezela = findViewById(R.id.beefRezela);
        burger = findViewById(R.id.burger);
        friedRice = findViewById(R.id.friedRice);
        next = findViewById(R.id.nextButton);

        kabab.setOnClickListener(this);
        kacchi.setOnClickListener(this);
        biriyani.setOnClickListener(this);
        beefRezela.setOnClickListener(this);
        burger.setOnClickListener(this);
        friedRice.setOnClickListener(this);
        next.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.burger) {
            db = FirebaseDatabase.getInstance();
            rf = db.getReference("Users");
            rf.child(userStr).child("preference").child("burger").setValue("yes");
            burger.setEnabled(false);

        }
        if (id == R.id.kabab) {
            db = FirebaseDatabase.getInstance();
            rf = db.getReference("Users");
            rf.child(userStr).child("preference").child("kabab").setValue("yes");
            burger.setEnabled(false);

        }
        if (id == R.id.kacchi) {
            db = FirebaseDatabase.getInstance();
            rf = db.getReference("Users");
            rf.child(userStr).child("preference").child("kacchi").setValue("yes");
            burger.setEnabled(false);

        }
        if (id == R.id.biriyani) {
            db = FirebaseDatabase.getInstance();
            rf = db.getReference("Users");
            rf.child(userStr).child("preference").child("biriyani").setValue("yes");
            burger.setEnabled(false);

        }
        if (id == R.id.beefRezela) {
            db = FirebaseDatabase.getInstance();
            rf = db.getReference("Users");
            rf.child(userStr).child("preference").child("beefRezela").setValue("yes");
            burger.setEnabled(false);

        }
        if (id == R.id.friedRice) {
            db = FirebaseDatabase.getInstance();
            rf = db.getReference("Users");
            rf.child(userStr).child("preference").child("friedRice").setValue("yes");
            burger.setEnabled(false);

        }
        if(id == R.id.nextButton){

            Intent intent = new Intent(getApplicationContext(), upload.class);
            intent.putExtra("message_key", userStr);
            startActivity(intent);
        }

    }

}