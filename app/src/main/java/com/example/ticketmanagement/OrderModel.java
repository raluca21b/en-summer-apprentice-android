package com.example.ticketmanagement;

import java.math.BigDecimal;

public class OrderModel {
    private  int numberOfTickets;
    private  String event;
    private  String category;



    private  String totalPrice;

    public OrderModel(int numberOfTickets, String event, String category, String totalPrice) {
        this.numberOfTickets = numberOfTickets;
        this.event = event;
        this.category = category;
        this.totalPrice = totalPrice;

    }
    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "numberOfTickets=" + numberOfTickets +
                ", event='" + event + '\'' +
                ", category='" + category + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}
