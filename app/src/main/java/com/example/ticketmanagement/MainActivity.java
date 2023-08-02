package com.example.ticketmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<EventsModel> eventsModels = new ArrayList<>();
    Events_RecyclerViewAdapter adapter;
    int[] eventImages = {R.drawable.untold,R.drawable.ec,
    R.drawable.fotbal,R.drawable.wine};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        adapter = new Events_RecyclerViewAdapter(this,eventsModels);
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
            if (eventItem.getEventName().toLowerCase().contains(text.toLowerCase())){
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
        String[] eventNames = getResources().getStringArray(R.array.events_names_full);
        String[] eventDescripts = getResources().getStringArray(R.array.events_description_full);
        String[] eventStartDate = getResources().getStringArray(R.array.startDates);
        String[] eventEndDates = getResources().getStringArray(R.array.endDates);
        for (int i = 0; i < eventNames.length; i++){
            eventsModels.add(new EventsModel(eventNames[i],eventDescripts[i],eventImages[i],eventStartDate[i],
                                             eventEndDates[i]));
        }
    }

}
