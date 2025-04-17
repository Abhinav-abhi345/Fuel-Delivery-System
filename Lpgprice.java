package com.example.fuelondemand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lpgprice extends AppCompatActivity {

    TextView t1,t2;
    String val1,type="LPG";
    Button Pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lpgprice);

        Pay = findViewById(R.id.Proceedtopay);

        t1 = findViewById(R.id.textViewPetrol);
        t2 = findViewById(R.id.textViewPetrolprice);

        val1 = getIntent().getStringExtra("data");
        t1.setText(val1);
        Double num =Double.parseDouble(val1);
        Double petrol = 38.89;
        Double delivery = 30.0;
        Double total = (num * petrol)+delivery;
        t2.setText(Double.toString(total));

        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lpgprice.this,CustomerDetails.class);
                intent.putExtra("tdata",type);
                intent.putExtra("quant",val1);
                startActivity(intent);
            }
        });

    }
}