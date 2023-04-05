package com.group8.saveit;

public class Customer {
    private String email;
    private String name;
    private int phone;
    private int streetNum;
    private String streetName;
    private String city;
    private String postalCode;

    public Customer(String email, String name, int phone, int streetNum, String streetName, String city, String postalCode) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
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
