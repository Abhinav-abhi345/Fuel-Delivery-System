package com.example.adminfuelondelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter4 extends RecyclerView.Adapter<MyAdapter4.MyViewHolder4> {

    Context context;
    ArrayList<DeliveredD> list;

    public MyAdapter4(Context context, ArrayList<DeliveredD> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.delivered_item,parent,false);
        return new MyViewHolder4(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder4 holder, int position) {
        DeliveredD deliveredD = list.get(position);
        holder.name.setText(deliveredD.getName());
        holder.mobile.setText(deliveredD.getMobile());
        holder.location.setText(deliveredD.getLocation());
        holder.fueltype.setText(deliveredD.getFueltype());
        holder.quantity.setText(deliveredD.getQuantity());
        holder.status.setText(deliveredD.getDelivery_status());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder4 extends RecyclerView.ViewHolder{

        TextView name,mobile,location,fueltype,quantity,status;

        public MyViewHolder4(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            mobile =itemView.findViewById(R.id.tvMobile);
            location =itemView.findViewById(R.id.tvLocation);
            fueltype =itemView.findViewById(R.id.tvFuel);
            quantity =itemView.findViewById(R.id.tvQuantity);
            status =itemView.findViewById(R.id.tvStatus);
        }
    }
}
