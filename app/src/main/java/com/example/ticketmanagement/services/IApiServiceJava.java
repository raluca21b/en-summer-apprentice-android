package com.example.ticketmanagement.services;


import com.example.ticketmanagement.dtos.EventDTO;
import com.example.ticketmanagement.dtos.OrderDTO;
import com.example.ticketmanagement.dtos.RequestOrderDTO;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiServiceJava {
    @GET("events/{eventId}")
    Call<EventDTO> getEventById(@Path("eventId") int eventId);
    @GET("events")
    Call<List<EventDTO>> getEvents(
           @Nullable @Query("venueID") Integer venueID,
            @Nullable @Query("eventTypeName") String eventTypeName
    );
    @GET("orders")
    Call<List<OrderDTO>> getOrders();

    @POST("orders")
    Call<OrderDTO> createOrder(@Body RequestOrderDTO requestOrderDTO);
}
