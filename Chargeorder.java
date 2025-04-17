package com.example.fuelondemand;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chargeorder extends AppCompatActivity {

    Button btproceed;
    String val;

    EditText Charge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chargeorder);

        Charge = findViewById(R.id.editField);
        btproceed = (Button)findViewById(R.id.proceed);

        btproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = Charge.getText().toString();

                if(TextUtils.isEmpty(val)){
                    Toast.makeText(Chargeorder.this,"Enter the Charge in kWh",Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    Intent intent = new Intent(Chargeorder.this, Chargeprice.class);
                    intent.putExtra("data", val);
                    startActivity(intent);
                }
                //Toast.makeText(Petrolorder.this,"Order Placed Suceessfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
}