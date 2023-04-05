package com.group8.saveit;

import java.util.ArrayList;

public class Order {
    String orderId;
    String orderDate;
    String restaurantName;
    ArrayList <String> bundles;
    int totalPrice;
    String orderStatus;

    public Order(String orderId, String orderDate, String restaurantName, ArrayList<String> bundles, int totalPrice, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.restaurantName = restaurantName;
        this.bundles = bundles;
        this.totalPrice = totalPrice;
        setOrderStatus(orderStatus);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public ArrayList<String> getBundles() {
        return bundles;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setBundles(ArrayList<String> bundles) {
        this.bundles = bundles;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderStatus(String orderStatus) {
        if(orderStatus.equals("1")){
            this.orderStatus = "Completed";
        }else{
            this.orderStatus = "Pending";
        }

    }
}
