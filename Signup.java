package com.example.deliveryboy;

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

    EditText Email,Password,Name,Mobile;
    FirebaseDatabase db;
    DatabaseReference reference;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

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
                    Toast.makeText(Signup.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Signup.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(Signup.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Delivery Boy");

                //mobile = String.valueOf(Mobile.getText());


                DeliveryBoy deliveryBoy = new DeliveryBoy(name,email,mobile,password);
                reference.child(name).setValue(deliveryBoy);


                Toast.makeText(Signup.this, "Registration successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Signup.this, login.class);
                startActivity(intent);
            }
        });

    }
}