package com.example.ticketmanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Events_RecyclerViewAdapter extends RecyclerView.Adapter<Events_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<EventsModel> eventsModels;

    public Events_RecyclerViewAdapter(Context context, ArrayList<EventsModel> eventsModels) {
        this.context = context;
        this.eventsModels = eventsModels;
    }

    @NonNull
    @Override
    public Events_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_event_row,parent,false);
        return new Events_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Events_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textViewName.setText(eventsModels.get(position).getEventName());
        holder.textViewDescription.setText(eventsModels.get(position).getEventDescription());
        holder.textViewStartDate.setText(eventsModels.get(position).getStartDate());
        holder.textViewEndDate.setText(eventsModels.get(position).getEndDate());
        holder.imageViewEvent.setImageResource(eventsModels.get(position).getImage());

        boolean isExpended = eventsModels.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpended ? View.VISIBLE : View.GONE);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewEvent;
        TextView textViewName;
        TextView textViewDescription;
        TextView textViewStartDate;
        TextView textViewEndDate;

        ConstraintLayout expandableLayout;
        ConstraintLayout headLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewEvent = itemView.findViewById(R.id.image_for_event);
            textViewName = itemView.findViewById(R.id.textView_event_name);
            textViewDescription = itemView.findViewById(R.id.textView_event_descr);
            textViewStartDate = itemView.findViewById(R.id.startDate);
            textViewEndDate = itemView.findViewById(R.id.endDate);

            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            headLayout = itemView.findViewById(R.id.headLayout);

            headLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventsModel eventModel = eventsModels.get(getAdapterPosition());
                    eventModel.setExpanded(!eventModel.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
