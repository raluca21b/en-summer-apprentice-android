package com.example.ticketmanagement;


import com.example.ticketmanagement.dtos.EventDTO;

public class EventsModel {

    private final int image;

    private EventDTO eventDTO;
    private boolean expanded;

    public EventsModel(int image, EventDTO eventDTO) {
        this.image = image;
        this.eventDTO = eventDTO;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getImage() {
        return image;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }
}
