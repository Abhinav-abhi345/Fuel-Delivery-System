package com.example.deliveryboy;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

public class DeliveredDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference reference;
    MyAdapter4 myAdapter4;
    ArrayList<DeliveredOrders> list;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delivered_details);

        recyclerView = findViewById(R.id.deliveredlist);
        reference = FirebaseDatabase.getInstance().getReference("Delivered Orders");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter4 = new MyAdapter4(this,list);
        recyclerView.setAdapter(myAdapter4);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DeliveredOrders deliveredOrders = dataSnapshot.getValue(DeliveredOrders.class);

                    list.add(deliveredOrders);
                }
                myAdapter4.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView = findViewById(R.id.idSearchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
    }

    public void searchList(String text){
        ArrayList<DeliveredOrders> searchList = new ArrayList<>();
        for(DeliveredOrders deliveredOrders : list){
            if(deliveredOrders.getName().toLowerCase().contains(text.toLowerCase())){
                searchList.add(deliveredOrders);

            }
        }
        myAdapter4.searchDataList(searchList);
    }
}