package com.example.ticketmanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketmanagement.dtos.EventDTO;
import com.example.ticketmanagement.dtos.OrderDTO;
import com.example.ticketmanagement.dtos.RequestOrderDTO;
import com.example.ticketmanagement.dtos.TicketCategoryDTO;
import com.example.ticketmanagement.services.ApiServiceJava;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Events_RecyclerViewAdapter extends RecyclerView.Adapter<Events_RecyclerViewAdapter.MyEventsViewHolder> {
    Context context;
    ArrayList<EventsModel> eventsModels;
    RadioGroup radioGroup;
    ApiServiceJava apiServiceJava;


    public Events_RecyclerViewAdapter(Context context, ArrayList<EventsModel> eventsModels,ApiServiceJava apiServiceJava) {
        this.context = context;
        this.eventsModels = eventsModels;
        this.apiServiceJava = apiServiceJava;
    }

    @NonNull
    @Override
    public Events_RecyclerViewAdapter.MyEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_event_row,parent,false);
        return new Events_RecyclerViewAdapter.MyEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Events_RecyclerViewAdapter.MyEventsViewHolder holder, int position) {
        holder.textViewName.setText(eventsModels.get(position).getEventDTO().getEventName());
        holder.textViewDescription.setText(eventsModels.get(position).getEventDTO().getEventDescription());
        holder.textViewStartDate.setText(eventsModels.get(position).getEventDTO().getStartDate());
        holder.textViewEndDate.setText(eventsModels.get(position).getEventDTO().getEndDate());
        holder.textViewLocation.setText(eventsModels.get(position).getEventDTO().getVenueDTO().getLocation());
        holder.textViewCapacity.setText(String.valueOf(eventsModels.get(position).getEventDTO().getVenueDTO().getCapacity()));
        holder.textViewType.setText(eventsModels.get(position).getEventDTO().getEventType());
        holder.imageViewEvent.setImageResource(eventsModels.get(position).getImage());


        boolean isExpended = eventsModels.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpended ? View.VISIBLE : View.GONE);
        radioGroup.removeAllViews();
        EventsModel eventModel = eventsModels.get(position);
        for (TicketCategoryDTO ticketCategory : eventModel.getEventDTO().getTicketsCategory()) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setText(ticketCategory.getDescription() + " - " + ticketCategory.getPrice());
            radioButton.setId(View.generateViewId());
            radioGroup.addView(radioButton);
        }

        if (radioGroup.getChildCount() > 0) {
            radioGroup.check(radioGroup.getChildAt(0).getId());
        }

    }


    @Override
    public int getItemCount() {
        return eventsModels.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<EventsModel> filteredList){
        eventsModels = filteredList;
        notifyDataSetChanged();
    }



    public class MyEventsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewEvent;
        TextView textViewName;
        TextView textViewDescription;
        TextView textViewStartDate;
        TextView textViewEndDate;
        TextView textViewLocation;
        TextView textViewCapacity;
        TextView textViewType;
        EditText editTextTickets;
        Button buyButton;

        ConstraintLayout expandableLayout;
        ConstraintLayout headLayout;
        public MyEventsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewEvent = itemView.findViewById(R.id.image_for_event);
            textViewName = itemView.findViewById(R.id.textView_event_name);
            textViewDescription = itemView.findViewById(R.id.textView_event_descr);
            textViewStartDate = itemView.findViewById(R.id.startDate);
            textViewEndDate = itemView.findViewById(R.id.endDate);
            textViewLocation = itemView.findViewById(R.id.textViewLocationDisplay);
            textViewCapacity = itemView.findViewById(R.id.textViewCapacityDisplay);
            textViewType = itemView.findViewById(R.id.textViewTypeDisplay);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            editTextTickets = itemView.findViewById(R.id.editTextNumber);
            headLayout = itemView.findViewById(R.id.headLayout);
            radioGroup = itemView.findViewById(R.id.radioGroupTicketType);


            headLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventsModel eventModel = eventsModels.get(getAdapterPosition());
                    eventModel.setExpanded(!eventModel.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            buyButton = itemView.findViewById(R.id.buttonBuy);
            buyButton.setOnClickListener(view -> {
                String inputText = editTextTickets.getText().toString().trim();
                int numberOfTickets = Integer.parseInt(inputText);
                if (!inputText.isEmpty()) {
                    EventDTO eventDTO = eventsModels.get(getAdapterPosition()).getEventDTO();
                    TicketCategoryDTO ticketCategoryDTO;
                    int radioButtonSelected = radioGroup.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = itemView.findViewById(radioButtonSelected);
                    String radioName = selectedRadioButton.getText().toString();
                    ticketCategoryDTO = findTicketCategoryByName(radioName);
                    RequestOrderDTO requestOrderDTO = new RequestOrderDTO(eventDTO.getEventID(),
                                                                          ticketCategoryDTO.getTicketCategoryID(),
                                                                            numberOfTickets);
                    apiServiceJava.placeOrder(requestOrderDTO, new Callback<OrderDTO>() {
                        @Override
                        public void onResponse(Call<OrderDTO> call, Response<OrderDTO> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(itemView.getContext(), "BOUGHT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                editTextTickets.setText("");
                            }else{
                                Log.e("API_DELETE", "Unsuccessful buy " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<OrderDTO> call, Throwable t) {
                            Log.e("API_DELETE", "Unsuccessful buy " + t.getMessage());

                        }
                    });
                } else {
                    Toast.makeText(itemView.getContext(), "Please enter the number of tickets", Toast.LENGTH_SHORT).show();
                }
            });
        }
        private TicketCategoryDTO findTicketCategoryByName(String categoryName) {
            EventsModel eventModel = eventsModels.get(getAdapterPosition());
            for (TicketCategoryDTO ticketCategory : eventModel.getEventDTO().getTicketsCategory()) {
                if (categoryName.contains(ticketCategory.getDescription())) {
                    return ticketCategory;
                }
            }
            return null;
        }


    }
}
