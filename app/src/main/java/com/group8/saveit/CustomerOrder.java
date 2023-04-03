package com.group8.saveit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomerOrder {
    private int orderId;
    private Date orderDate;
    private int customerId;
    private String deliveryOption;
    private String address;
    private boolean isCompleted;

    private ArrayList<FoodBundle> orderedFoodBundles=new ArrayList<>();

    public CustomerOrder(int orderId, int customerId, String deliveryOption, String address) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryOption = deliveryOption;
        this.address = address;
        orderDate= Calendar.getInstance().getTime();
        isCompleted=false;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public ArrayList<FoodBundle> getOrderedFoodBundles() {
        return orderedFoodBundles;
    }

    public void setOrderedFoodBundles(ArrayList<FoodBundle> orderedFoodBundles) {
        this.orderedFoodBundles = orderedFoodBundles;
    }
}
