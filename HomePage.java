package com.example.fuelondemand;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.map);

        bottomNavigationView.setOnItemSelectedListener(item -> {
                    startActivity(new Intent(getApplicationContext(),MapActivity.class));
                    finish();
                    return true;

        });

        Button petroladd = findViewById(R.id.petroladd);

        petroladd.setOnClickListener(v -> {

            Intent intent = new Intent(this, Petrolorder.class);
            startActivity(intent);
        });

        Button dieseladd = findViewById(R.id.dieseladd);

        dieseladd.setOnClickListener(v -> {

            Intent intent = new Intent(this, Dieselorder.class);
            startActivity(intent);
        });

        Button chargeadd = findViewById(R.id.chargeadd);

        chargeadd.setOnClickListener(v -> {

            Intent intent = new Intent(this, Chargeorder.class);
            startActivity(intent);
        });

        Button cngadd = findViewById(R.id.cngadd);

        cngadd.setOnClickListener(v -> {

            Intent intent = new Intent(this, Cngorder.class);
            startActivity(intent);
        });;

        Button lpgadd = findViewById(R.id.lpgadd);

        lpgadd.setOnClickListener(v -> {

            Intent intent = new Intent(this, Lpgorder.class);
            startActivity(intent);
        });;




    }
}