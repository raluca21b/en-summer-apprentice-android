package com.example.ticketmanagement.dtos;

import java.io.Serializable;

public class OrderPatchDTO implements Serializable {
    private int orderId;
    private int eventId;
    private String ticketDescription;
    private int numberOfTickets;

    public OrderPatchDTO(int orderId, int eventId, String ticketDescription, int numberOfTickets) {
        this.orderId = orderId;
        this.eventId = eventId;
        this.ticketDescription = ticketDescription;
        this.numberOfTickets = numberOfTickets;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String toString() {
        return "OrderPatchDTO{" +
                "orderId=" + orderId +
                ", eventId=" + eventId +
                ", ticketDescription='" + ticketDescription + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
