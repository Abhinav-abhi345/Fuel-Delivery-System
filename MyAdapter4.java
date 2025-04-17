package com.example.deliveryboy;

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
    ArrayList<DeliveredOrders> list;

    public MyAdapter4(Context context, ArrayList<DeliveredOrders> list) {
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
        DeliveredOrders deliveredOrders = list.get(position);
        holder.name.setText(deliveredOrders.getName());
        holder.mobile.setText(deliveredOrders.getMobile());
        holder.location.setText(deliveredOrders.getLocation());
        holder.fueltype.setText(deliveredOrders.getFueltype());
        holder.quantity.setText(deliveredOrders.getQuantity());
        holder.status.setText(deliveredOrders.getDelivery_status());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void searchDataList(ArrayList<DeliveredOrders> searchList){
        list =searchList;
        notifyDataSetChanged();
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
