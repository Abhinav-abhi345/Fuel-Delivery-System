package com.example.adminfuelondelivery;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeliveryBoyDetails extends AppCompatActivity {

    RecyclerView recyclerView2;
    DatabaseReference refe;
    MyAdapter3 myAdapter3;
    ArrayList<DeliveryBoyD> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delivery_boy_details);

        recyclerView2 = findViewById(R.id.deliveryboylist);
        refe = FirebaseDatabase.getInstance().getReference("Delivery Boy");
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter3 = new MyAdapter3(this,list);
        recyclerView2.setAdapter(myAdapter3);

        refe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DeliveryBoyD deliveryBoyD = dataSnapshot.getValue(DeliveryBoyD.class);

                    list.add(deliveryBoyD);
                }
                myAdapter3.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}