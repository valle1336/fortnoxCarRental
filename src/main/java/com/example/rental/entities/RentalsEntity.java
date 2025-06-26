package com.example.rental.entities;

import java.time.LocalDate;

public class RentalsEntity {
    private int id;
    private int carId;
    private String driverName;
    private int driverAge;
    private LocalDate startDate;
    private LocalDate endDate;
    private int revenue;

    public RentalsEntity(int id, int carId, String driverName, int driverAge, LocalDate startDate, LocalDate endDate, int revenue) {
        this.id = id;
        this.carId = carId;
        this.driverName = driverName;
        this.driverAge = driverAge;
        this.startDate = startDate;
        this.endDate = endDate;
        this.revenue = revenue;
    }

    // Getters och setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}
