package com.example.ticketmanagement;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketmanagement.dtos.EventDTO;
import com.example.ticketmanagement.dtos.OrderDTO;
import com.example.ticketmanagement.services.ApiServiceJava;
import com.example.ticketmanagement.services.ApiServiceNet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersActivity extends AppCompatActivity {
    ArrayList<OrderModel> orderModels;
    String eventName;
    Order_RecyclerViewAdapter orderAdapter;
    ApiServiceJava apiServiceJava;
    RecyclerView recyclerViewOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiServiceJava = new ApiServiceJava();
        orderModels = new ArrayList<>();
        setContentView(R.layout.activity_order);

        ImageView goBackImageView = findViewById(R.id.go_back);

        goBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerViewOrder = findViewById(R.id.ordersRecyclerView);

        setUpOrderDTO(); // Initiate the API call to populate orderModels
    }

    private void setUpOrderDTO() {
        apiServiceJava.getOrders(new Callback<List<OrderDTO>>() {
            @Override
            public void onResponse(Call<List<OrderDTO>> call, Response<List<OrderDTO>> response) {
                if(response.isSuccessful()){
                    Log.i("API", "Success response in orders DTO: " + response.code());
                    List<OrderDTO> orderDTOS = response.body();
                    Log.i("API",orderDTOS.get(0).toString());
                    Log.i("MODEL","Length of ordersDTO "+orderDTOS.size());
                    for(OrderDTO orderDTO: orderDTOS){
                        apiServiceJava.getEventById(orderDTO.getEventID(), new Callback<EventDTO>() {
                                    @Override
                                    public void onResponse(Call<EventDTO> call, Response<EventDTO> response) {
                                        if(response.isSuccessful()){
                                            eventName = response.body().getEventName();
                                            orderModels.add(new OrderModel(orderDTO.getNumberOfTickets(), eventName
                                                    , orderDTO.getTicketCategory().getDescription()
                                                    , String.valueOf(orderDTO.getTotalPrice())));
                                            Log.i("API","SUCCESS EVENT NAME");
                                            orderAdapter = new Order_RecyclerViewAdapter(OrdersActivity.this, orderModels);
                                            recyclerViewOrder.setAdapter(orderAdapter);
                                            recyclerViewOrder.setLayoutManager(new LinearLayoutManager(OrdersActivity.this));
                                        }else{
                                            Log.e("API", "Unsuccessful in EventName orders response: " + response.code());
                                        }

                                    }
                                    @Override
                                    public void onFailure(Call<EventDTO> call, Throwable t) {
                                        Log.e("API", "Call failed on eventName: " + t.getMessage());
                                    }
                                });

                    }
                } else {
                    Log.e("API", "Unsuccessful in get all orders response: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<OrderDTO>> call, Throwable t) {
                Log.e("API", "Call failed on get orders: " + t.getMessage());
            }
        });

    }




}
