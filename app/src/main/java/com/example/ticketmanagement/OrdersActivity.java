package com.example.ticketmanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    ArrayList<OrderModel> orderModels = new ArrayList<>();
    Order_RecyclerViewAdapter orderAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        ImageView goBackImageView = findViewById(R.id.go_back);

        goBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView recyclerViewOrder = findViewById(R.id.ordersRecyclerView);

        setUpOrderModels();

        orderAdapter = new Order_RecyclerViewAdapter(this,orderModels);
        recyclerViewOrder.setAdapter(orderAdapter);
        recyclerViewOrder.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpOrderModels() {
        orderModels.add(new OrderModel(1,"Untold","Standard","750.00","750.00"));
        orderModels.add(new OrderModel(2,"EC","VIP","900.00","1800.00"));


    }
}
