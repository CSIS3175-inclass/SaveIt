package com.group8.saveit;

public class Restaurant {
    private int rid;
    private String name;
    private String start_time;
    private String end_time;
    private String streetName;
    private String city;
    private String postalCode;

    public Restaurant(int rid, String name, String start_time, String end_time, String streetName, String city, String postalCode) {
        this.rid = rid;
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.streetName = streetName;
        this.city = city;
        this.postalCode = postalCode;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
