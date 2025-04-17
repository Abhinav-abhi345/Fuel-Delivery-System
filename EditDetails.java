package com.example.deliveryboy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditDetails extends AppCompatActivity {

    EditText name,mobile,fueltype,quantity,status;
    String Name,Mobile,FuelType,Quantity,Status;
    Button update;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_details);

        update = findViewById(R.id.btUpdate);
        mobile = findViewById(R.id.mobileno);
        fueltype = findViewById(R.id.fueltype);
        quantity = findViewById(R.id.quantity);
        status = findViewById(R.id.status);
        name = findViewById(R.id.name);

        Name = name.getText().toString();
        Mobile = mobile.getText().toString();
        Quantity = quantity.getText().toString();
        FuelType = fueltype.getText().toString();
        Status = status.getText().toString();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(Name,Mobile,Quantity,FuelType,Status);
            }
        });

    }

    private void updateData(String Name,String Mobile,String FuelType,String Quantity,String Status) {
        HashMap hashMap = new HashMap();
        hashMap.put("name",Name);
        hashMap.put("mobile",Mobile);
        hashMap.put("fueltype",FuelType);
        hashMap.put("quantity",Quantity);
        hashMap.put("delivery_status",Status);

        databaseReference = FirebaseDatabase.getInstance().getReference("Delivered Orders");
        databaseReference.child(Name).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    name.setText("");
                    mobile.setText("");
                    fueltype.setText("");
                    quantity.setText("");
                    status.setText("");
                    Toast.makeText(EditDetails.this,"Successfully Modify the Data",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditDetails.this,"Failed to Update",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}