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

public class Update extends AppCompatActivity {

    Button Update;

    String price,Username;
    EditText Price,username;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        Update = findViewById(R.id.update);
        Price = findViewById(R.id.editText2);
        username = findViewById(R.id.editText1);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 price = Price.getText().toString();
                 Username = username.getText().toString();

                updateData(price);
            }
        });
    }

    private void updateData(String price) {
        HashMap hashMap = new HashMap();
        hashMap.put("payment_method",price);

        databaseReference = FirebaseDatabase.getInstance().getReference("orders");
        databaseReference.child(Username).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    username.setText("");
                    Price.setText("");
                    Toast.makeText(Update.this,"Successfully Updated the Data",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Update.this,"Failed to Update",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}