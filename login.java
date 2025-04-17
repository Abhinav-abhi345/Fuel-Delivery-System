package com.example.fuelondemand;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {

    EditText loginUsername,loginPassword;
    TextView Register;
    Button Login;
    FirebaseDatabase db;
    DatabaseReference reference;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.emailId);
        loginPassword = findViewById(R.id.pwd);
        Register = findViewById(R.id.register);
        Login = findViewById(R.id.login);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,Registration.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateemail() | !validatepassword()){

                }else{
                    checkUser();
                }
            }
        });

    }


    public Boolean validateemail(){
        String val = loginUsername.getText().toString();
        if(val.isEmpty()){
            loginUsername.setError("Enter Username");
            return false;
        }else{
            loginUsername.setError(null);
            return true;
        }
    }
    public Boolean validatepassword(){
        String val = loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("Enter Password");
            return false;
        }else{
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        db = FirebaseDatabase.getInstance();
        reference = db.getReference("users");
        Query checkUserDB = reference.orderByChild("name").equalTo(userUsername);

        checkUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    loginUsername.setError(null);
                    String passwordfromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if(passwordfromDB.equals(userPassword)){
                        loginUsername.setError(null);

                        Toast.makeText(login.this,"Login successfully!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this,HomePage.class);
                        startActivity(intent);
                        finish();
                    }else{
                        loginPassword.setError("Invalid Data");
                        loginPassword.requestFocus();
                    }
                }else {
                    loginUsername.setError("User does not exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}