package com.example.ticketmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketmanagement.dtos.EventDTO;
import com.example.ticketmanagement.dtos.TicketCategoryDTO;
import com.example.ticketmanagement.dtos.VenueDTO;
import com.example.ticketmanagement.services.ApiServiceJava;
import com.example.ticketmanagement.services.ApiServiceNet;

import java.math.BigDecimal;
import java.text.BreakIterator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<EventsModel> eventsModels;
    Events_RecyclerViewAdapter adapter;
    ApiServiceJava apiServiceJava;

    int[] eventImages = {R.drawable.untold,R.drawable.ec,
    R.drawable.fotbal,R.drawable.wine};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiServiceJava = new ApiServiceJava();
        eventsModels = new ArrayList<>();
        setContentView(R.layout.activity_main);
        ImageView menuIcon = findViewById(R.id.menu_main);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        RecyclerView recyclerViewEvent = findViewById(R.id.eventsRecyclerView);

        setUpEventsModel();

        adapter = new Events_RecyclerViewAdapter(this,eventsModels,apiServiceJava);
        recyclerViewEvent.setAdapter(adapter);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));

        EditText editText = findViewById(R.id.searchEventName);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    public void filter(String text){
        ArrayList<EventsModel> filteredList = new ArrayList<>();
        for(EventsModel eventItem : eventsModels){
            if (eventItem.getEventDTO().getEventName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(eventItem);
            }
        }
        adapter.filterList(filteredList);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_items);


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });

        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==  R.id.action_item1) {
            Intent intent = new Intent(this,OrdersActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpEventsModel(){
        apiServiceJava.getEvents(null, null, new Callback<List<EventDTO>>() {
            @Override
            public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
                if(response.isSuccessful()){
                    Log.e("API", "Success in getEvents response: " + response.code());
                    List<EventDTO> eventDTOS = response.body();
                    Log.e("API",String.valueOf(eventDTOS.size()));

                    int imageIndex = 0;
                    for (EventDTO eventDTO : eventDTOS){
                        eventsModels.add(new EventsModel(eventImages[imageIndex],eventDTO));
                        imageIndex += 1;
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    Log.e("API", "Unsuccessful in get Events response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<EventDTO>> call, Throwable t) {
                 Log.e("API", "Call failed: " + t.getMessage());
            }
        });

    }

}
