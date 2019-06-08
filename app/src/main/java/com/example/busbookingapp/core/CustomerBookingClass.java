package com.example.busbookingapp.core;

import java.util.ArrayList;

/**
 * A class for information about a customers booking
 */
public class CustomerBookingClass {
    private String customerId; //makes the bookingId
    private String customerNo;
    private String additionalNo;
    private String bookingId;
    private boolean isCustomerIncluded; //included in it?
    private ArrayList<String> arrayListCustomers;

    public CustomerBookingClass() {
        arrayListCustomers = new ArrayList<>();
    }

    public CustomerBookingClass(String customerId, String bookingId, boolean isCustomerIncluded, String customerNo, String additionalNo) {
        this.customerId = customerId;
        this.bookingId = bookingId;
        this.isCustomerIncluded = isCustomerIncluded;
        this.customerNo = customerNo;
        this.additionalNo = additionalNo;
        arrayListCustomers = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public boolean isCustomerIncluded() {
        return isCustomerIncluded;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public String getAdditionalNo() {
        return additionalNo;
    }

    public ArrayList<String> getArrayListCustomers() {
        return arrayListCustomers;
    }

    public void setArrayListCustomers(ArrayList<String> arrayListCustomers) {
        this.arrayListCustomers = arrayListCustomers;
    }
}
