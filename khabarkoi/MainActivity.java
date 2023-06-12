package com.example.khabarkoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText signInUsernameEditText, signInPasswordEditText;
    private Button signInButton;
    private TextView signUpTextView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://khabarkoi-default-rtdb.firebaseio.com");

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInUsernameEditText = findViewById(R.id.signInUsernameId);
        signInPasswordEditText = findViewById(R.id.signInPasswordId);
        signInButton = findViewById(R.id.signInLoginButtonId);
        signUpTextView = findViewById(R.id.signUPTextViewId);

        signUpTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.signInLoginButtonId){

            String username = signInUsernameEditText.getText().toString();
            String password = signInPasswordEditText.getText().toString();
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(MainActivity.this, "please enter your mobile or password", Toast.LENGTH_SHORT).show();
            }
            else{

                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild((username))){
                            final String getpassword = snapshot.child(username).child("password").getValue(String.class);

                            if(getpassword.equals(password)){
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setCancelable(false);
                                builder.setView(R.layout.progress_layout);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                Intent intent = new Intent(getApplicationContext(), home.class);
                                intent.putExtra("message_key", username);
                                startActivity(intent);

                            }else{

                                Toast.makeText(MainActivity.this,"wrong password", Toast.LENGTH_SHORT).show();

                            }
                        }else{
                            Toast.makeText(MainActivity.this,"username doesnot exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        else if (id == R.id.signUPTextViewId){
            Intent intent = new Intent(getApplicationContext(), reg.class);
            startActivity(intent);
        }

    }
}