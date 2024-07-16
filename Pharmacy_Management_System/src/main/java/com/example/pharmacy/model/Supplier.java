package com.example.pharmacy.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private int id;
    private String name;
    private String contactInfo;
    private List<Drug> drugs;

    public Supplier(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.drugs = new ArrayList<>();
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void addDrug(Drug drug) {
        this.drugs.add(drug);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
