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

import com.example.ticketmanagement.dtos.OrderPatchDTO;
import com.example.ticketmanagement.services.ApiServiceJava;
import com.example.ticketmanagement.services.ApiServiceNet;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_RecyclerViewAdapter extends RecyclerView.Adapter<Order_RecyclerViewAdapter.MyOrderViewHolder> {
    Context context;
    ArrayList<OrderModel> orderModels;
    ApiServiceNet apiServiceNet;

    public Order_RecyclerViewAdapter(Context context, ArrayList<OrderModel> orderModels, ApiServiceNet apiServiceNet) {
        this.context = context;
        this.apiServiceNet = apiServiceNet;
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

                        apiServiceNet.deleteOrder(orderModels.get(position).getOrderID(), new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context,"Order deleted Succesfully",Toast.LENGTH_LONG).show();
                                    orderModels.remove(position);
                                    notifyItemRemoved(position);
                                    dialog.dismiss();
                                }else{

                                    Log.e("API_DELETE", "Unsuccessful delete " + orderModels.get(position).getOrderID()
                                                                                        +"response:" + response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.e("API_DELETE", "Call failed delete : " + t.getMessage());
                            }
                        });

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
                OrderPatchDTO orderPatchDTO = new OrderPatchDTO(orderModel.getOrderID(),orderModel.getEventID(),
                                                                orderModel.getCategory(),orderModel.getNumberOfTickets());

                apiServiceNet.patchOrder(orderPatchDTO, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context,"Order updated Succesfully",Toast.LENGTH_LONG).show();
                            notifyDataSetChanged();
                            dialog.dismiss();

                        }else{
                            Toast.makeText(context,"Something went wrong...",Toast.LENGTH_LONG).show();
                            Log.e("API_DELETE", "Unsuccessful update response:" + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("API_DELETE", "Call failure update:" + t.getMessage());

                    }
                });

            });

            dialog.show();
        }
    }
}
