package com.example.deliveryboy;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    EditText loginDUsername,loginDPassword;
    TextView Register;
    Button Login;
    FirebaseDatabase database;
    DatabaseReference reference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        loginDUsername = findViewById(R.id.emailId);
        loginDPassword = findViewById(R.id.pwd);
        Register = findViewById(R.id.register);
        Login = findViewById(R.id.login);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,Signup.class);
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
        String val = loginDUsername.getText().toString();
        if(val.isEmpty()){
            loginDUsername.setError("Enter Username");
            return false;
        }else{
            loginDUsername.setError(null);
            return true;
        }
    }
    public Boolean validatepassword(){
        String val = loginDPassword.getText().toString();
        if(val.isEmpty()){
            loginDPassword.setError("Enter Password");
            return false;
        }else{
            loginDPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userDUsername = loginDUsername.getText().toString().trim();
        String userDPassword = loginDPassword.getText().toString().trim();

        database = FirebaseDatabase.getInstance();
        reference1 = database.getReference("Delivery Boy");
        Query checkUserDB = reference1.orderByChild("name").equalTo(userDUsername);

        checkUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    loginDUsername.setError(null);
                    String dpasswordfromDB = snapshot.child(userDUsername).child("password").getValue(String.class);

                    if(dpasswordfromDB.equals(userDPassword)){
                        loginDUsername.setError(null);

                        Toast.makeText(login.this,"Login successfully!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this,HomePage.class);
                        startActivity(intent);
                        finish();
                    }else{
                        loginDPassword.setError("Invalid Data");
                        loginDPassword.requestFocus();
                    }
                }else {
                    loginDUsername.setError("User does not exist");
                    loginDUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}