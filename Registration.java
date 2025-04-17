package com.example.fuelondemand;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
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

import com.example.fuelondemand.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {


    //ActivityMainBinding binding;
    //String name,email,password,mobile;
    EditText Email,Password,Name,Mobile;
    FirebaseDatabase db;
    DatabaseReference reference;
    Button Register;

   FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        Email = findViewById(R.id.emailid);
        Password = findViewById(R.id.Password);
        Register = findViewById(R.id.btRegister);
        Name = findViewById(R.id.name);
        Mobile = findViewById(R.id.mobileno);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password, mobile, name;
                email = Email.getText().toString();
                password = Password.getText().toString();
                name = Name.getText().toString();
                mobile = Mobile.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Registration.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Registration.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Registration.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(Registration.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("users");

                //mobile = String.valueOf(Mobile.getText());


                Users users = new Users(name,email,mobile,password);
                reference.child(name).setValue(users);


                Toast.makeText(Registration.this, "Registration successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registration.this, login.class);
                startActivity(intent);
            }
        });

    }
}

