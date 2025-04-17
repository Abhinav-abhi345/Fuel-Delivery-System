package com.example.adminfuelondelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder3> {

    Context con;
    ArrayList<DeliveryBoyD> list;

    public MyAdapter3(Context con, ArrayList<DeliveryBoyD> list) {
        this.con = con;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter3.MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(con).inflate(R.layout.deliveryboy,parent,false);
        return new MyAdapter3.MyViewHolder3(vi);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder3, int position3) {
        DeliveryBoyD deliveryBoyD = list.get(position3);
        holder3.name.setText(deliveryBoyD.getName());
        holder3.mobile.setText(deliveryBoyD.getMobile());
        holder3.email.setText(deliveryBoyD.getEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder3 extends RecyclerView.ViewHolder{
        TextView name,mobile,email;

        public MyViewHolder3(@NonNull View itemV) {
            super(itemV);

            name = itemV.findViewById(R.id.tvName);
            mobile =itemV.findViewById(R.id.tvMobile);
            email =itemV.findViewById(R.id.tvEmail);
        }
    }
}

