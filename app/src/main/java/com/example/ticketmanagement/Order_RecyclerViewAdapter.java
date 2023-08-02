package com.example.ticketmanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Order_RecyclerViewAdapter extends RecyclerView.Adapter<Order_RecyclerViewAdapter.MyOrderViewHolder> {
    Context context;
    ArrayList<OrderModel> orderModels;

    public Order_RecyclerViewAdapter(Context context, ArrayList<OrderModel> orderModels) {
        this.context = context;
        this.orderModels = orderModels;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_order_row,parent,false);
        return new Order_RecyclerViewAdapter.MyOrderViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position) {
        holder.textViewNumberOfTickets.setText(String.valueOf(orderModels.get(position).getNumberOfTickets()));
        holder.textViewEventName.setText(orderModels.get(position).getEvent());
        holder.textViewCategory.setText(orderModels.get(position).getCategory());
        holder.textViewPrice.setText(orderModels.get(position).getPrice());
        holder.textViewTotalPrice.setText(orderModels.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public static class MyOrderViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNumberOfTickets;
        TextView textViewEventName;
        TextView textViewCategory;
        TextView textViewPrice;
        TextView textViewTotalPrice;

        ConstraintLayout headOrderLayout;
        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumberOfTickets = itemView.findViewById(R.id.textViewNoTicketsDisplay);
            textViewEventName = itemView.findViewById(R.id.textViewEventNameDisplay);
            textViewCategory = itemView.findViewById(R.id.textViewCategoryDisplay);
            textViewPrice = itemView.findViewById(R.id.textViewPriceDisplay);
            textViewTotalPrice = itemView.findViewById(R.id.textViewTotalPriceDisplay);

        }
    }
}