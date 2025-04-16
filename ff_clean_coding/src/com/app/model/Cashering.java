package com.app.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Cashering {
    private int id;

    private LocalDateTime date;

    private String operationNumber;

    private String userNumber;

    private LocalDateTime openAt;

    private LocalDateTime closeAt;

    public Cashering (LocalDateTime date, String operationNumber) {
        this.date = date;
        this.operationNumber = operationNumber;
    }

    public Cashering() {
    }

    public Cashering(int id, LocalDateTime date, String operationNumber, String userNumber, LocalDateTime openAt, LocalDateTime closeAt) {
        this.id = id;
        this.date = date;
        this.operationNumber = operationNumber;
        this.userNumber = userNumber;
        this.openAt = openAt;
        this.closeAt = closeAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public LocalDateTime getOpenAt() {
        return openAt;
    }

    public void setOpenAt(LocalDateTime openAt) {
        this.openAt = openAt;
    }

    public LocalDateTime getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(LocalDateTime closeAt) {
        this.closeAt = closeAt;
    }
}
