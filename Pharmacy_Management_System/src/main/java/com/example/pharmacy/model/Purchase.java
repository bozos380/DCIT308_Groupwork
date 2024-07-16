package com.example.pharmacy.model;

import java.time.LocalDateTime;

public class Purchase {
    private int id;
    private int drugId;
    private String buyer;
    private LocalDateTime dateTime;
    private int quantity;

    public Purchase(int drugId, String buyer, LocalDateTime dateTime, int quantity) {
        this.drugId = drugId;
        this.buyer = buyer;
        this.dateTime = dateTime;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", drugId=" + drugId +
                ", buyer='" + buyer + '\'' +
                ", dateTime=" + dateTime +
                ", quantity=" + quantity +
                '}';
    }
}
