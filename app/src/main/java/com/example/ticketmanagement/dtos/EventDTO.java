package com.example.ticketmanagement.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class EventDTO implements Serializable {
    private int eventID;
    private VenueDTO venueDTO;
    private String eventType;
    private String eventDescription;
    private String eventName;
    private String startDate;
    private String endDate;
    private List<TicketCategoryDTO> ticketsCategory;

    public EventDTO(int eventID, VenueDTO venueDTO, String eventType, String eventDescription, String eventName, String startDate, String endDate, List<TicketCategoryDTO> ticketsCategory) {
        this.eventID = eventID;
        this.venueDTO = venueDTO;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketsCategory = ticketsCategory;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public VenueDTO getVenueDTO() {
        return venueDTO;
    }

    public void setVenueDTO(VenueDTO venueDTO) {
        this.venueDTO = venueDTO;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<TicketCategoryDTO> getTicketsCategory() {
        return ticketsCategory;
    }

    public void setTicketsCategory(List<TicketCategoryDTO> ticketsCategory) {
        this.ticketsCategory = ticketsCategory;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventID=" + eventID +
                ", venueDTO=" + venueDTO +
                ", eventType='" + eventType + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventName='" + eventName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", ticketsCategory=" + ticketsCategory +
                '}';
    }
}
