package com.example.busbookingapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.busbookingapp.core.BookingClass;
import com.example.busbookingapp.viewmodel.BookingsViewModel;

import java.util.ArrayList;

public class AdminSecondaryFragment extends Fragment {
    BookingsViewModel model;

    EditText editTextDestination;
    EditText editTextTime;
    EditText editTextDate;
    EditText editTextFare;
    EditText editTextTotalSeats;
    EditText editTextDriverId;
    EditText editTextDriverNo;

    Button buttonAddBooking;

    public AdminSecondaryFragment() {
    }

    public static AdminSecondaryFragment newInstance() {
        AdminSecondaryFragment adminSecondaryFragment = new AdminSecondaryFragment();
        return adminSecondaryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_booking, container, false);
        editTextTime = v.findViewById(R.id.idBookingTime);
        editTextDate = v.findViewById(R.id.idBookingDate);
        editTextDestination = v.findViewById(R.id.idBookingDestination);
        editTextFare = v.findViewById(R.id.idBookingFare);
        editTextTotalSeats = v.findViewById(R.id.idBookingSeats);
        editTextDriverId = v.findViewById(R.id.idBookingDriverId);
        editTextDriverNo = v.findViewById(R.id.idBookingDriverNo);

        buttonAddBooking = v.findViewById(R.id.idBookingButton);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //view model for this fragment
        model = ViewModelProviders.of(getActivity()).get(BookingsViewModel.class);

        buttonAddBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingClass e = new BookingClass(editTextDestination.getText().toString()
                        , editTextDate.getText().toString()
                        , editTextTime.getText().toString()
                        , editTextFare.getText().toString()
                        , editTextTotalSeats.getText().toString()
                        , editTextDriverId.getText().toString()
                        , editTextDriverNo.getText().toString());
                model.addBooking(e);
                getActivity().onBackPressed();
            }
        });
    }
}
