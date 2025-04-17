package com.example.adminfuelondelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<OrderDetails> list;

    public MyAdapter(Context context, ArrayList<OrderDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.delivery_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderDetails orderDetails = list.get(position);
        holder.name.setText(orderDetails.getName());
        holder.mobile.setText(orderDetails.getMobile());
        holder.location.setText(orderDetails.getLocation());
        holder.fueltype.setText(orderDetails.getFueltype());
        holder.quantity.setText(orderDetails.getQuantity());
        holder.payment.setText(orderDetails.getPayment_method());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,mobile,location,fueltype,quantity,payment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            mobile =itemView.findViewById(R.id.tvMobile);
            location =itemView.findViewById(R.id.tvLocation);
            fueltype =itemView.findViewById(R.id.tvFuel);
            quantity =itemView.findViewById(R.id.tvQuantity);
            payment =itemView.findViewById(R.id.tvPayment);
        }
    }
}
