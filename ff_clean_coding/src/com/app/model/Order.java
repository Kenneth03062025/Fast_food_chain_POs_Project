package com.app.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private String orderNumber;

    private String scheduleNumber;
    private LocalDateTime placeAt;

    private String status;

    public Order(int id, String orderNumber, String scheduleNumber, LocalDateTime placeAt, String status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.scheduleNumber = scheduleNumber;
        this.placeAt = placeAt;
        this.status = status;
    }

    public Order(String orderNumber, String scheduleNumber) {
        this.orderNumber = orderNumber;
        this.scheduleNumber = scheduleNumber;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getScheduleNumber() {
        return scheduleNumber;
    }

    public void setScheduleNumber(String scheduleNumber) {
        this.scheduleNumber = scheduleNumber;
    }

    public LocalDateTime getPlaceAt() {
        return placeAt;
    }

    public void setPlaceAt(LocalDateTime placeAt) {
        this.placeAt = placeAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
