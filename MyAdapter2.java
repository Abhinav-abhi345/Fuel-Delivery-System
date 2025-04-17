package com.example.adminfuelondelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2> {

    Context cont;
    ArrayList<CustomersD> list;

    public MyAdapter2(Context conte, ArrayList<CustomersD> list) {
        this.cont = conte;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter2.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ve = LayoutInflater.from(cont).inflate(R.layout.customers,parent,false);
        return new MyAdapter2.MyViewHolder2(ve);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder2, int position2) {
        CustomersD customersD = list.get(position2);
        holder2.name.setText(customersD.getName());
        holder2.mobile.setText(customersD.getMobile());
        holder2.email.setText(customersD.getEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{
        TextView name,mobile,email;

        public MyViewHolder2(@NonNull View itemV) {
            super(itemV);

            name = itemV.findViewById(R.id.tvName);
            mobile =itemV.findViewById(R.id.tvMobile);
            email =itemV.findViewById(R.id.tvEmail);
        }
    }
}
