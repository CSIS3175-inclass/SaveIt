package com.group8.saveit;

public class Manager {

    private String email;
    private String name;
    private String phoneNum;
    private String password;
    private String startTime;
    private  String endTime;

    public Manager(String email, String name, String phoneNum, String password, String startTime, String endTime) {
        this.email = email;
        this.name = name;
        this.phoneNum = phoneNum;
        this.password = password;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
