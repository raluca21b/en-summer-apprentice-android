package com.example.ticketmanagement;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class Order_RecyclerViewAdapter extends RecyclerView.Adapter<Order_RecyclerViewAdapter.MyOrderViewHolder> {
    Context context;
    ArrayList<OrderModel> orderModels;

    public Order_RecyclerViewAdapter(Context context, ArrayList<OrderModel> orderModels) {
        this.context = context;
        this.orderModels = orderModels;
        Log.i("HELP","I AM HERE IN Order_RecyclerViewAdapter");
        Log.i("HELP","ORDERS FOUND " + getItemCount());
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_order_row, parent, false);
        return new Order_RecyclerViewAdapter.MyOrderViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position) {
        Log.i("HELP","I AM HERE IN onBINDVIEWHOLDER");
        holder.textViewNumberOfTickets.setText(String.valueOf(orderModels.get(position).getNumberOfTickets()));
        holder.textViewEventName.setText(orderModels.get(position).getEvent());
        holder.textViewCategory.setText(orderModels.get(position).getCategory());
        holder.textViewTotalPrice.setText(orderModels.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class MyOrderViewHolder extends RecyclerView.ViewHolder {
        Button editButton;
        Button deleteButton;
        TextView textViewNumberOfTickets;
        TextView textViewEventName;
        TextView textViewCategory;
        TextView textViewTotalPrice;



        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumberOfTickets = itemView.findViewById(R.id.textViewNoTicketsDisplay);
            textViewEventName = itemView.findViewById(R.id.textViewEventNameDisplay);
            textViewCategory = itemView.findViewById(R.id.textViewCategoryDisplay);
            textViewTotalPrice = itemView.findViewById(R.id.textViewTotalPriceDisplay);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
            deleteButton.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    showDeleteConfirmationDialog(position);
                }
            });
            editButton = itemView.findViewById(R.id.buttonEdit);
            editButton.setOnClickListener(view -> {
                openEditDialog();
            });

        }
        private void showDeleteConfirmationDialog(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Confirm Delete")
                    .setMessage("Are you sure you want to delete?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        orderModels.remove(position);
                        notifyItemRemoved(position);
                        dialog.dismiss();
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        }

        private void openEditDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            View dialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.edit_order_dialog, null);


            EditText editTextNumberOfTickets = dialogView.findViewById(R.id.editTextNumberOfTickets);
            Spinner spinnerCategory = dialogView.findViewById(R.id.spinnerCategory);
            Button saveChangesButton = dialogView.findViewById(R.id.saveChangesButton);
            ImageView imageViewClose = dialogView.findViewById(R.id.imageViewClose);

            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_item, new String[]{"Standard", "VIP"});
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategory.setAdapter(categoryAdapter);

            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            imageViewClose.setOnClickListener(view -> {
                dialog.dismiss();
            });

            saveChangesButton.setOnClickListener(v -> {

                String numberOfTicketsText = editTextNumberOfTickets.getText().toString();

                int numberOfTickets;
                if (numberOfTicketsText.isEmpty()) {
                    numberOfTickets = orderModels.get(getAdapterPosition()).getNumberOfTickets();
                    Toast.makeText(context, "The number of tickets not set, it will remain default", Toast.LENGTH_LONG).show();
                } else {
                    numberOfTickets = Integer.parseInt(numberOfTicketsText);
                }

                String selectedCategory = spinnerCategory.getSelectedItem().toString();

                OrderModel orderModel = orderModels.get(getAdapterPosition());
                orderModel.setNumberOfTickets(numberOfTickets);
                orderModel.setCategory(selectedCategory);

                notifyDataSetChanged();

                dialog.dismiss();
            });

            dialog.show();
        }
    }
}
