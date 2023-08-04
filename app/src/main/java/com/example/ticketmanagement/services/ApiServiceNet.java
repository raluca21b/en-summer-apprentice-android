package com.example.ticketmanagement.services;

import com.example.ticketmanagement.dtos.EventDTO;
import com.example.ticketmanagement.dtos.OrderPatchDTO;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceNet{
    private final String BASE_URL = "http://10.0.2.2:7071/";
    private final IApiServiceNet apiServiceNet;

    public ApiServiceNet() {
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

        this.apiServiceNet = retrofit.create(IApiServiceNet.class);
    }



    public void patchOrder(OrderPatchDTO orderPatchDTO, Callback<Void> callback) {
        Call<Void> patchOrderCall = apiServiceNet.patchOrder(orderPatchDTO);
        patchOrderCall.enqueue(callback);
    }

    public void deleteOrder(int id, Callback<Void> callback) {
        Call<Void> deleteOrderCall = apiServiceNet.deleteOrder(id);
        deleteOrderCall.enqueue(callback);
    }
}
