package com.example.busbookingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busbookingapp.adapter.CustomerBookingListRecyclerViewAdapter;
import com.example.busbookingapp.adapter.CustomerListRecyclerViewAdapter;
import com.example.busbookingapp.adapter.ListRecyclerViewAdapter;
import com.example.busbookingapp.core.BookingClass;
import com.example.busbookingapp.core.CustomerBookingClass;
import com.example.busbookingapp.viewmodel.BookingsViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminMainFragment extends Fragment{
    BookingsViewModel mBookingViewModel;

    Context context;

    RecyclerView recyclerViewBookings;
    RecyclerView recyclerViewCustomerBookings;
    RecyclerView recyclerViewCustomerList;

    FirebaseDatabase database;
    DatabaseReference mRef;

    //from top
    Button buttonAddBooking;
    TextView textViewFilledSeats;
    TextView textViewTotalSeats;
    TextView textViewFare;
    TextView textViewDriverId;
    TextView textViewDriverNo;
    Button buttonDownloadData;
    Button buttonUploadData;

    TextView textViewCustomerName;
    TextView textViewCustomerNo;

    public AdminMainFragment() {
        // Required empty public constructor
    }

    public static AdminMainFragment newInstance() {
        AdminMainFragment fragment = new AdminMainFragment();
        return fragment;
    }

    public void uploadToFirebase() {
        mRef.child("Bookings").setValue(mBookingViewModel.getMutableLiveDataAdminBookings().getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context, "Bookings Uploaded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void downloadFromFirebase() {
        mRef.child("TEST").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d : dataSnapshot.getChildren()) {
                    String s = dataSnapshot.getValue(String.class);
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRef.child("Bookings").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<BookingClass> arrayList = new ArrayList<>();
                if(dataSnapshot.hasChildren()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        arrayList.add(ds.getValue(BookingClass.class));
                    }
                }
                mBookingViewModel.getMutableLiveDataAdminBookings().setValue(arrayList);
                //Toast.makeText(getContext(), "Bookings Downloaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Bookings Download Failed. "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference();

        downloadFromFirebase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_main, container, false);
        recyclerViewBookings = v.findViewById(R.id.idRecyclerViewBookingsList);
        recyclerViewCustomerBookings = v.findViewById(R.id.idListCustomerBookings);
        recyclerViewCustomerList = v.findViewById(R.id.idListCustomers);

        //from top
        buttonAddBooking = v.findViewById(R.id.idButtonAddBooking);
        textViewFilledSeats = v.findViewById(R.id.idSeatsFilled);
        textViewTotalSeats = v.findViewById(R.id.idTotalSeats);
        textViewFare = v.findViewById(R.id.idFare);
        textViewDriverId = v.findViewById(R.id.idBookingDriverId);
        textViewDriverNo = v.findViewById(R.id.idDriverNo);
        buttonDownloadData = v.findViewById(R.id.idButtonDownload);
        buttonUploadData = v.findViewById(R.id.idButtonUpload);

        textViewCustomerName = v.findViewById(R.id.idCustomoerName);
        textViewCustomerNo = v.findViewById(R.id.idCustomerNo);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //view model for this fragment
        mBookingViewModel = ViewModelProviders.of(getActivity()).get(BookingsViewModel.class);
        try {
            mBookingViewModel.init();
        } catch (Exception e) {
            Toast.makeText(context,""+e.getMessage(), Toast.LENGTH_SHORT);
        }

        //setting admin booking recycler view
        recyclerViewBookings.setHasFixedSize(false);
        final ListRecyclerViewAdapter recyclerViewAdapter = new ListRecyclerViewAdapter(getContext(), mBookingViewModel.getMutableLiveDataAdminBookings().getValue());
        recyclerViewBookings.setAdapter(recyclerViewAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewBookings.setLayoutManager(manager);

        //setting customer booking recycler view
        recyclerViewCustomerBookings.setHasFixedSize(false);
        final CustomerBookingListRecyclerViewAdapter customerBookingRecyclerViewAdapter = new CustomerBookingListRecyclerViewAdapter(getContext(), mBookingViewModel.getCustomerBookingList());
        recyclerViewCustomerBookings.setAdapter(customerBookingRecyclerViewAdapter);
        LinearLayoutManager customerBookingManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewCustomerBookings.setLayoutManager(customerBookingManager);



        //setting customer recycler view
        recyclerViewCustomerList.setHasFixedSize(false);
        final CustomerListRecyclerViewAdapter customerRecyclerViewAdapter = new CustomerListRecyclerViewAdapter(getContext(), mBookingViewModel.getCustomerList());
        recyclerViewCustomerList.setAdapter(customerRecyclerViewAdapter);
        LinearLayoutManager customerListManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewCustomerList.setLayoutManager(customerListManager);



        //observing the lists (mutable live datas) and notifying the respective recycler view adapters of any change
        mBookingViewModel.getMutableLiveDataAdminBookings().observe(this, new Observer<ArrayList<BookingClass>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BookingClass> bookingClassArrayList) {
                recyclerViewAdapter.updateData(bookingClassArrayList);
                //Toast.makeText(getContext(), "Admin Bookings Updated", Toast.LENGTH_SHORT).show();
                Log.d("TEST", "called");
            }
        });

        mBookingViewModel.getMutableLiveDataCustomerBooking().observe(this, new Observer<BookingClass>() {
            @Override
            public void onChanged(@Nullable BookingClass bookingClass) {
                textViewDriverId.setText(bookingClass.getBusId());
                textViewDriverNo.setText(bookingClass.getBusDriverNumber());
                textViewFare.setText(bookingClass.getFare());
                textViewFilledSeats.setText(bookingClass.getArrayListCustomerNames().size()+"");
                textViewTotalSeats.setText(bookingClass.getTotalSeats());

                //Toast.makeText(getContext(), "Customer Bookings Updated", Toast.LENGTH_SHORT).show();

                customerBookingRecyclerViewAdapter.updateData(bookingClass.getArrayListCustomerBookingList());
            }
        });

        mBookingViewModel.getMutableLiveDataCustomers().observe(this, new Observer<CustomerBookingClass>() {
            @Override
            public void onChanged(@Nullable CustomerBookingClass customerBookingClass) {
                customerRecyclerViewAdapter.updateData(customerBookingClass.getArrayListCustomers());
                textViewCustomerName.setText(customerBookingClass.getCustomerId());
                textViewCustomerNo.setText(customerBookingClass.getCustomerNo()+"/"+customerBookingClass.getAdditionalNo());
                //Toast.makeText(getContext(), "Customers Updated", Toast.LENGTH_SHORT).show();
            }
        });

        buttonAddBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AdminSecondaryFragment adminSecondaryFragment = AdminSecondaryFragment.newInstance();
                //fragmentTransaction.setCustomAnimations(R.anim.slide_up_and_deaccelerate, R.anim.slide_right, R.anim.slide_right, R.anim.slidedown_and_accelerate);
                fragmentTransaction.replace(R.id.idFrameLayout, adminSecondaryFragment, "ADMIN_SECONDARY_FRAGMENT");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                //mBookingViewModel.addBooking(new BookingClass("Mountain View, CA", "Soon", "5:50AM", "Sponsored", "1", "E231", "Self"));

            }
        });

        buttonDownloadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFromFirebase();
            }
        });

        buttonUploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.child("test").setValue("hey");
                uploadToFirebase();
            }
        });
    }

}
