package com.group8.saveit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomerOrder {
    private int orderId;
    private String orderDate;
    private int customerId;
    private String customerEmail;
    private String deliveryOption;
    private String address;
    private boolean isCompleted;

    private ArrayList<FoodBundle> orderedFoodBundles=new ArrayList<>();

    public CustomerOrder(int orderId, int customerId, String deliveryOption, String address) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryOption = deliveryOption;
        this.address = address;
        Date date = Calendar.getInstance().getTime();
        orderDate = date.toString();
        isCompleted=false;
    }
    public CustomerOrder(String customerEmail, String deliveryOption, String address) {
        this.customerEmail = customerEmail;
        this.deliveryOption = deliveryOption;
        this.address = address;
        Date date = Calendar.getInstance().getTime();
        orderDate = date.toString();
        isCompleted=false;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
