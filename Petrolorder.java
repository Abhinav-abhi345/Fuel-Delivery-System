package com.example.fuelondemand;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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

public class Petrolorder extends AppCompatActivity {

    Button btproceed;
    String val;

    EditText Litre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_petrolorder);

        //Spinner myspinner  = (Spinner) findViewById(R.id.spinner3);
        Litre = findViewById(R.id.editField);
        btproceed = (Button)findViewById(R.id.proceed);

        btproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = Litre.getText().toString();

                if(TextUtils.isEmpty(val)){
                    Toast.makeText(Petrolorder.this,"Enter Amount of Fuel",Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    Intent intent = new Intent(Petrolorder.this, Petrolprice.class);
                    intent.putExtra("data", val);
                    startActivity(intent);
                }
                //Toast.makeText(Petrolorder.this,"Order Placed Suceessfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
}