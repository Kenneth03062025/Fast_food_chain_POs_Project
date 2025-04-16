package com.app.model;

public class Stocks {

    private int id;

    private String scheduleNumber;

    private String itemNumber;

    private String itemName;

    private int quantity;

    private int itemSold;

    public Stocks(int id, String scheduleNumber, String itemNumber, int quantity, int itemSold) {
        this.id = id;
        this.scheduleNumber = scheduleNumber;
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.itemSold = itemSold;
    }

    public Stocks(int id, String scheduleNumber, String itemNumber, String itemName, int quantity, int itemSold) {
        this.id = id;
        this.scheduleNumber = scheduleNumber;
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemSold = itemSold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScheduleNumber() {
        return scheduleNumber;
    }

    public void setScheduleNumber(String scheduleNumber) {
        this.scheduleNumber = scheduleNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemSold() {
        return itemSold;
    }

    public void setItemSold(int itemSold) {
        this.itemSold = itemSold;
    }
}
