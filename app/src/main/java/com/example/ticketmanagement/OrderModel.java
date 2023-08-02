package com.example.ticketmanagement;

import java.math.BigDecimal;

public class OrderModel {
    private final int numberOfTickets;
    private final String event;
    private final String category;
    private final String price;
    private final String totalPrice;
    private boolean expanded;

    public OrderModel(int numberOfTickets, String event, String category, String price, String totalPrice) {
        this.numberOfTickets = numberOfTickets;
        this.event = event;
        this.category = category;
        this.price = price;
        this.totalPrice = totalPrice;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expandable) {
        this.expanded = expandable;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public String getEvent() {
        return event;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
