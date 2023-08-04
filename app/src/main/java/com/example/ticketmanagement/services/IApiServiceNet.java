package com.example.ticketmanagement.services;

import com.example.ticketmanagement.dtos.EventDTO;
import com.example.ticketmanagement.dtos.OrderPatchDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiServiceNet {

    @PATCH("api/Order/Patch")
    Call<Void> patchOrder(@Body OrderPatchDTO orderPatchDTO);

    @DELETE("api/Order/Delete/{id}")
    Call<Void> deleteOrder(@Query("id") int id);
}
