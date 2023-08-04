package com.example.ticketmanagement.services;


import com.example.ticketmanagement.dtos.EventDTO;
import com.example.ticketmanagement.dtos.OrderDTO;
import com.example.ticketmanagement.dtos.RequestOrderDTO;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.OutputKeys;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceJava {
    private final String BASE_URL = "http://10.0.2.2:8080/";
    private final IApiServiceJava apiServiceJava;


    public ApiServiceJava() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                                      .addInterceptor(interceptor)
                                                      .build();
        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .client(okHttpClient)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

        this.apiServiceJava = retrofit.create(IApiServiceJava.class);
    }
    public void getEventById(int id, Callback<EventDTO> callback) {
        Call<EventDTO> getByIdCall = apiServiceJava.getEventById(id);
        getByIdCall.enqueue(callback);
    }
    public void getEvents(Integer venueId, String eventType, Callback<List<EventDTO>> callback){
        Call<List<EventDTO>> getAllEvents = apiServiceJava.getEvents(venueId,eventType);
        getAllEvents.enqueue(callback);
    }

    public void getOrders(Callback<List<OrderDTO>> callback){
        Call<List<OrderDTO>> getAllOrders = apiServiceJava.getOrders();
        getAllOrders.enqueue(callback);
    }

    public void placeOrder(RequestOrderDTO requestOrderDTO,Callback<OrderDTO> callback){
        Call<OrderDTO> placeOrder = apiServiceJava.createOrder(requestOrderDTO);
        placeOrder.enqueue(callback);
    }

}
