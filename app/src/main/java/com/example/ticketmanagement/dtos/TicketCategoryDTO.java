package com.example.ticketmanagement.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class TicketCategoryDTO implements Serializable {
    private int ticketCategoryID;
    private String description;
    private BigDecimal price;

    public TicketCategoryDTO(int ticketCategoryID, String description, BigDecimal price) {
        this.ticketCategoryID = ticketCategoryID;
        this.description = description;
        this.price = price;
    }

    public int getTicketCategoryID() {
        return ticketCategoryID;
    }

    public void setTicketCategoryID(int ticketCategoryID) {
        this.ticketCategoryID = ticketCategoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketCategoryDTO{" +
                "ticketCategoryID=" + ticketCategoryID +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
