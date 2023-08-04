package com.example.ticketmanagement.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDTO implements Serializable {
    private int eventID;
    private String orderedAt;
    private TicketCategoryDTO ticketCategory;
    private int numberOfTickets;
    private BigDecimal totalPrice;

    public OrderDTO(int eventID, String orderedAt, TicketCategoryDTO ticketCategory, int numberOfTickets, BigDecimal totalPrice) {
        this.eventID = eventID;
        this.orderedAt = orderedAt;
        this.ticketCategory = ticketCategory;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(String orderedAt) {
        this.orderedAt = orderedAt;
    }

    public TicketCategoryDTO getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategoryDTO ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "eventID=" + eventID +
                ", orderedAt='" + orderedAt + '\'' +
                ", ticketCategory=" + ticketCategory +
                ", numberOfTickets=" + numberOfTickets +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
