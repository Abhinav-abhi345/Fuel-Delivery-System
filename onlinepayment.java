package com.example.fuelondemand;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class onlinepayment extends AppCompatActivity {

    Button payonlpay;

    RadioGroup Radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onlinepayment);

        Radiogroup = findViewById(R.id.radioGroup);
        payonlpay = (Button)findViewById(R.id.payonlinepayment);

        payonlpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Radiogroup.getCheckedRadioButtonId();

                if(id == R.id.radioButton)
                {
                    Intent g = new Intent(Intent.ACTION_VIEW);
                    g.setData(Uri.parse("https://pay.google.com"));
                    startActivity(g);
                } else if (id == R.id.radioButton5) {
                    Intent p = new Intent(Intent.ACTION_VIEW);
                    p.setData(Uri.parse("https://paytm.com"));
                    startActivity(p);
                }else {
                    Intent ph = new Intent(Intent.ACTION_VIEW);
                    ph.setData(Uri.parse("https://www.phonepe.com"));
                    startActivity(ph);

                }

                Toast.makeText(onlinepayment.this,"Order Placed Suceessfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
}