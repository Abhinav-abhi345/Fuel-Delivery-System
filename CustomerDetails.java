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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerDetails extends AppCompatActivity {

    EditText Name,Mobile;
    EditText Location;
    Button paycash,payonline;
    String location,quant,paymethod,name,mobile;
    String type;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_details);

        Name = findViewById(R.id.getName);
        Mobile = findViewById(R.id.getMobile);
        Location = findViewById(R.id.editLocation);
        paycash = findViewById(R.id.paycash);
        payonline = findViewById(R.id.payonline);




        paycash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymethod ="Cash on Delivery";
                location = Location.getText().toString();
                name = Name.getText().toString();
                mobile = Mobile.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(CustomerDetails.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(CustomerDetails.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(CustomerDetails.this, "Enter Location", Toast.LENGTH_SHORT).show();
                    return;
                }


                quant = getIntent().getStringExtra("quant");
                type = getIntent().getStringExtra("tdata");

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("orders");

                Orders orders = new Orders(name, mobile,location, type,quant,paymethod);
                reference.child(name).setValue(orders);

                Toast.makeText(CustomerDetails.this,"Order Placed Suceessfully",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(CustomerDetails.this,CustomerDetails.class);
            }
        });


        payonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymethod ="Paid";
                location = Location.getText().toString();
                name = Name.getText().toString();
                mobile = Mobile.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(CustomerDetails.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(CustomerDetails.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(CustomerDetails.this, "Enter Location", Toast.LENGTH_SHORT).show();
                    return;
                }

                quant = getIntent().getStringExtra("quant");
                type = getIntent().getStringExtra("tdata");

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("orders");
                Orders orders = new Orders(name, mobile, location, type,quant,paymethod);
                reference.child(name).setValue(orders);

                Intent intent = new Intent(CustomerDetails.this,onlinepayment.class);
                startActivity(intent);
            }
        });

    }

}