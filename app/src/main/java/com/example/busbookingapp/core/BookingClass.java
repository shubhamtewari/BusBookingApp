package com.example.busbookingapp.core;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing bookings added by the admins, which will be used, by the customers, to book
 */
public class BookingClass implements Serializable {
    private String destination;
    private String date;
    private String time;
    private String fare;
    private String seatsFilled;
    private String totalSeats;
    private String busDriverNumber;
    private String busId;
    private ArrayList<String> arrayListCustomerNames;
    private ArrayList<CustomerBookingClass> arrayListCustomerBookingList;

    public BookingClass() {
        arrayListCustomerNames = new ArrayList<>();
        arrayListCustomerBookingList = new ArrayList<>();
    }

    public BookingClass(String destination, String date, String time, String fare, String totalSeats, String busId, String busDriverNumber) {
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.fare = fare;
        this.totalSeats = totalSeats;
        this.busId = busId;
        this.busDriverNumber = busDriverNumber;
        seatsFilled = "0";
        arrayListCustomerBookingList = new ArrayList<>();
        arrayListCustomerNames = new ArrayList<>();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getSeatsFilled() {
        return seatsFilled;
    }

    public void setSeatsFilled(String seatsFilled) {
        this.seatsFilled = seatsFilled;
    }

    public String getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(String totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getBusDriverNumber() {
        return busDriverNumber;
    }

    public void setBusDriverNumber(String busDriverNumber) {
        this.busDriverNumber = busDriverNumber;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public ArrayList<String> getArrayListCustomerNames() {
        return arrayListCustomerNames;
    }

    public void setArrayListCustomerNames(ArrayList<String> arrayListCustomerNames) {
        this.arrayListCustomerNames = arrayListCustomerNames;
    }

    public ArrayList<CustomerBookingClass> getArrayListCustomerBookingList() {
        return arrayListCustomerBookingList;
    }

    public void setArrayListCustomerBookingList(ArrayList<CustomerBookingClass> arrayListCustomerBookingList) {
        this.arrayListCustomerBookingList = arrayListCustomerBookingList;
    }
}
