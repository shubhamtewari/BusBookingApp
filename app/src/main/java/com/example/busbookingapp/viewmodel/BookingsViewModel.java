package com.example.busbookingapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.busbookingapp.core.BookingClass;
import com.example.busbookingapp.core.CustomerBookingClass;

import java.util.ArrayList;

public class BookingsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<BookingClass>> mutableLiveDataAdminBookings;
    MutableLiveData<Boolean> isUpdating;
    private MutableLiveData<BookingClass> mutableLiveDataCustomerBooking;
    private MutableLiveData<CustomerBookingClass> mutableLiveDataCustomers;

    boolean ranOnce = false;

    public void init() throws Exception{
        if(ranOnce) {
            //Log.d("TEST", "no run once");
            return;
        }
        else {
            mutableLiveDataAdminBookings = new MutableLiveData<>();
            mutableLiveDataAdminBookings.setValue(new ArrayList<BookingClass>());
            mutableLiveDataCustomerBooking = new MutableLiveData<>();
            mutableLiveDataCustomerBooking.setValue(new BookingClass());
            mutableLiveDataCustomers = new MutableLiveData<>();
            mutableLiveDataCustomers.setValue(new CustomerBookingClass());
            isUpdating = new MutableLiveData<>();
            ranOnce = true;
            //Log.d("TEST", "ran once");
        }
    }

    public MutableLiveData<ArrayList<BookingClass>> getMutableLiveDataAdminBookings() {
        return mutableLiveDataAdminBookings;
    }

    public MutableLiveData<BookingClass> getMutableLiveDataCustomerBooking() {
        return mutableLiveDataCustomerBooking;
    }

    public MutableLiveData<CustomerBookingClass> getMutableLiveDataCustomers() {
        return mutableLiveDataCustomers;
    }


    //add bus booking
    public void addBooking(BookingClass e) {
        ArrayList<BookingClass> bookingClassArrayList = mutableLiveDataAdminBookings.getValue();
        bookingClassArrayList.add(e);
        mutableLiveDataAdminBookings.setValue(bookingClassArrayList);
    }

    //delete bus booking
    public void deleteBooking(int i) {
        ArrayList<BookingClass> bookingClassArrayList = mutableLiveDataAdminBookings.getValue();
        bookingClassArrayList.remove(i);
        mutableLiveDataAdminBookings.setValue(bookingClassArrayList);
    }

    //select the booking to display details in the respective recycler view
    public void selectBooking(int i) {
        BookingClass bookingClass = mutableLiveDataAdminBookings.getValue().get(i);
        mutableLiveDataCustomerBooking.setValue(bookingClass);
    }

    //select the customer booking to display details in the respective recycler view
    public void selectCustomerBooking(int i) {
        mutableLiveDataCustomers.setValue(mutableLiveDataCustomerBooking.getValue().getArrayListCustomerBookingList().get(i));
    }

    //get the customer booking list
    public ArrayList<CustomerBookingClass> getCustomerBookingList() {
        ArrayList<CustomerBookingClass> arrayList;
        if(mutableLiveDataCustomerBooking.getValue()==null) {
            arrayList = new ArrayList<>();
            return arrayList;
        }
        else {
            arrayList = mutableLiveDataCustomerBooking.getValue().getArrayListCustomerBookingList();
            return arrayList;
        }
    }

    //get the customer list
    public ArrayList<String> getCustomerList() {
        ArrayList<String> arrayList;
        if(mutableLiveDataCustomers.getValue()==null) {
            arrayList = new ArrayList<>();
            return arrayList;
        }
        else {
            arrayList = mutableLiveDataCustomers.getValue().getArrayListCustomers();
            return arrayList;
        }
    }
}
