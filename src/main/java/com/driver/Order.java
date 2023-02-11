package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
    private String timeInString;

    public Order(String id, String deliveryTime) {
        this.id = id;
        String timeArray[] = deliveryTime.split(":");
        int hh = Integer.parseInt(timeArray[0]);
        int mm = Integer.parseInt(timeArray[1]);
        this.deliveryTime = hh * 60 + mm;
        this.timeInString = deliveryTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getTimeInString() {
        return timeInString;
    }

    public void setTimeInString(String timeInString) {
        this.timeInString = timeInString;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}
