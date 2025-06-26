package com.example.rental.entities;

public class CarEntity {
    private int id;
    private String name;
    private int pricePerDay;

    public CarEntity(int id, String name, int pricePerDay) {
        this.id = id;
        this.name = name;
        this.pricePerDay = pricePerDay;
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

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}

