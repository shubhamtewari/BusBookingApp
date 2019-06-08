package com.example.busbookingapp.core;

public class CustomerClass {
    private String uid;
    private String name;
    private String branch;
    private String course;
    private int year;
    private String phoneNumber;
    private String email;

    public CustomerClass(String uid, String name, String branch, String course, int year, String phoneNumber, String email) {
        this.uid = uid;
        this.name = name;
        this.branch = branch;
        this.course = course;
        this.year = year;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
