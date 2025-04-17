package com.example.adminfuelondelivery;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText Email,Password,Name;
    FirebaseDatabase database;
    DatabaseReference reference1;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        Email = findViewById(R.id.emailid);
        Name = findViewById(R.id.name);
        Password = findViewById(R.id.Password);
        Register = findViewById(R.id.btRegister);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        String name,email,password;
        name = Name.getText().toString();
        email = Email.getText().toString();
        password = Password.getText().toString();

        if(name.isEmpty()){
            Toast.makeText(Signup.this, "Enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()){
            Toast.makeText(Signup.this, "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(Signup.this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        database = FirebaseDatabase.getInstance();
        reference1 = database.getReference("Admin");

        Admin admin = new Admin(name,email,password);
        reference1.child(name).setValue(admin);

        Toast.makeText(Signup.this, "Registration successfully!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Signup.this, login.class);
        startActivity(i);
            }
        });

    }
}