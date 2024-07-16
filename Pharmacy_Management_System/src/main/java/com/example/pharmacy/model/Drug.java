package com.example.pharmacy.model;

import java.util.ArrayList;
import java.util.List;

public class Drug {
    private int id;
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;
    private int supplierId;
    //private int minThreshold;
    //private int maxThreshold;
    private List<Purchase> purchaseHistory;

    public Drug(String name, String manufacturer, double price, int quantity, int supplierId) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.supplierId = supplierId;
        //this.minThreshold = minThreshold;
        //this.maxThreshold = maxThreshold;
        this.purchaseHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", supplierId=" + supplierId +
                '}';
    }

    public List<Purchase> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void addPurchase(Purchase purchase) {
        this.purchaseHistory.add(purchase);
    }
}
