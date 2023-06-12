package com.example.khabarkoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reg extends AppCompatActivity implements View.OnClickListener{

    private EditText signUpFullname, signUpUsername, signUPEmail, signUpPassword, signUpConfirmPassword;
    private Button signUpButton;
    private TextView signUpTextView;
    private FirebaseAuth mAuth;

    FirebaseDatabase db;
    DatabaseReference rf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mAuth = FirebaseAuth.getInstance();

        signUpFullname = findViewById(R.id.signUpFullnameId);
        signUpUsername = findViewById(R.id.signUpUsernameId);
        signUPEmail = findViewById(R.id.signUpEmailId);
        signUpPassword = findViewById(R.id.signUpPasswordId);
        signUpConfirmPassword = findViewById(R.id.signUpConfirmPasswordId);
        signUpButton = findViewById(R.id.signUpLoginId);
        signUpTextView = findViewById(R.id.signUPTextViewId);

        signUpButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.signUpLoginId){
            userRegister();
        }
        else if (id == R.id.signUPTextViewId){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }

    private void userRegister(){

        String email = signUPEmail.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();
        String fullname = signUpFullname.getText().toString().trim();
        String username = signUpUsername.getText().toString().trim();
        String confirmpassword = signUpConfirmPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            signUPEmail.setError("Enter an email address");
            signUPEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signUPEmail.setError("Enter a valid email address");
            signUPEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            signUpPassword.setError("Enter a password");
            signUpPassword.requestFocus();
            return;
        }

        if(password.length() < 8){

            signUpPassword.setError("Minimum length of a password shoudl be 8");
            signUpPassword.requestFocus();
            return;
        }

        if(!password.equals(confirmpassword)){

            signUpConfirmPassword.setError("your password didn't matched");
            signUpConfirmPassword.requestFocus();
            return;
        }

        if(!email.isEmpty() && !password.isEmpty() && !username.isEmpty() && !confirmpassword.isEmpty() && !fullname.isEmpty()){

            Users users = new Users(fullname, username, email, password, confirmpassword, "");
            db = FirebaseDatabase.getInstance();
            rf = db.getReference("Users");

            rf.child(username).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    signUPEmail.setText("");
                    signUpFullname.setText("");
                    signUpUsername.setText("");
                    signUpPassword.setText("");
                    signUpConfirmPassword.setText("");
                    Toast.makeText(reg.this, "wellcome to our foodie world", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), preference.class);
                    intent.putExtra("message_key", username);
                    startActivity(intent);
                }
            });
        }

    }
}