package com.group8.saveit;

public class Customer {
    private String email;
    private String name;
    private String phone;
    private String streetName;
    private String city;
    private String password;
    private String postalCode;



    public Customer(String email, String password, String name, String phone, String streetName, String city, String postalCode) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.streetName = streetName;
        this.city = city;
        this.password=password;
        this.postalCode = postalCode;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
