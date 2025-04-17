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

public class UpdateOrder extends AppCompatActivity {

    EditText name,mobile,location,quantity,fuel;
    String msg ="Delivered";
    Button update;

    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_order);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobileno);
        quantity = findViewById(R.id.quantity);
        fuel = findViewById(R.id.fueltype);
        update = findViewById(R.id.btUpdate);
        location = findViewById(R.id.loc);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fuel, Quantity, Mobile, Name,Location;
                Fuel = fuel.getText().toString();
                Quantity = quantity.getText().toString();
                Name = name.getText().toString();
                Mobile = mobile.getText().toString();
                Location = location.getText().toString();

                if (TextUtils.isEmpty(Fuel)) {
                    Toast.makeText(UpdateOrder.this, "Enter Fuel Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Quantity)) {
                    Toast.makeText(UpdateOrder.this, "Enter Quantity", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(UpdateOrder.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Mobile)) {
                    Toast.makeText(UpdateOrder.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Location)) {
                    Toast.makeText(UpdateOrder.this, "Enter Location", Toast.LENGTH_SHORT).show();
                    return;
                }

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Delivered Orders");


                DeliveredOrders deliveredOrders = new DeliveredOrders(Name,Mobile,Location,Fuel,Quantity,msg);
                reference.child(Name).setValue(deliveredOrders);


                Toast.makeText(UpdateOrder.this, "Update Order successfully!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}