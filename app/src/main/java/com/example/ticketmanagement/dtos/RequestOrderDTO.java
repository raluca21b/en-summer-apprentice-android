package com.example.ticketmanagement.dtos;

import java.io.Serializable;

public class RequestOrderDTO implements Serializable {
    private int eventID;
    private int ticketCategoryID;
    private int numberOfTickets;

    public RequestOrderDTO(int eventID, int ticketCategoryID, int numberOfTickets) {
        this.eventID = eventID;
        this.ticketCategoryID = ticketCategoryID;
        this.numberOfTickets = numberOfTickets;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getTicketCategoryID() {
        return ticketCategoryID;
    }

    public void setTicketCategoryID(int ticketCategoryID) {
        this.ticketCategoryID = ticketCategoryID;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String toString() {
        return "RequestOrderDTO{" +
                "eventID=" + eventID +
                ", ticketCategoryID=" + ticketCategoryID +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
