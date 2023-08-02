package com.example.ticketmanagement;


public class EventsModel {
    private final String eventName;
    private final String eventDescription;
    private final int image;
    private final String startDate;
    private final String endDate;
    private boolean expanded;

    public EventsModel(String eventName, String eventDescription, int image, String startDate, String endDate) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getImage() {
        return image;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
