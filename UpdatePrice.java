package com.example.adminfuelondelivery;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class UpdatePrice extends AppCompatActivity {

    String type;
    Button Update;
    EditText Price;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_price);


        Spinner myspinner  = (Spinner) findViewById(R.id.spinner);
        Update = findViewById(R.id.button);
        Price = findViewById(R.id.price);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(UpdatePrice.this,
                android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.fuels));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapter);

        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = myspinner.getSelectedItem().toString();
               // Toast.makeText(UpdatePrice.this, type, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price = Price.getText().toString();

                updateData(price);
            }
        });




    }

    private void updateData(String price) {
        HashMap hashMap = new HashMap();
        hashMap.put(type,price);

        databaseReference = FirebaseDatabase.getInstance().getReference("Fuel Price");
        databaseReference.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Price.setText("");
                    Toast.makeText(UpdatePrice.this,"Successfully Updated the Data",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdatePrice.this,"Failed to Update",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}