package com.example.ticketmanagement;

public class OrderModel {
    private int orderID;
    private int eventID;
    private  int numberOfTickets;
    private  String event;
    private  String category;
    private  String totalPrice;

    public OrderModel(int orderID, int eventID, int numberOfTickets, String event, String category, String totalPrice) {
        this.orderID = orderID;
        this.eventID = eventID;
        this.numberOfTickets = numberOfTickets;
        this.event = event;
        this.category = category;
        this.totalPrice = totalPrice;

    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
