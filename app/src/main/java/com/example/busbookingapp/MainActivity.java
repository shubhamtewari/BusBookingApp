package com.example.busbookingapp;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.busbookingapp.adapter.RecyclerViewAdapter;
import com.example.busbookingapp.core.BookingClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    RecyclerView recyclerView;

    //view model stuff*******************
    ArrayList<BookingClass> arrayListBookings;
    //*************************************

    void updateUI(FirebaseUser mUser) {
        if(mUser==null) {
            Intent i = new Intent(getApplicationContext(), SplashPageActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        arrayListBookings = new ArrayList<>();

        updateUI(mUser);

        Toolbar toolbar = (Toolbar) findViewById(R.id.idToolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.idRecyclerViewContainer);

        arrayListBookings.add(new BookingClass("Bus Stand, Kotdwar", "12/12/23", "7:50AM", "175RS", "45", "123", "Badoni"));
        arrayListBookings.add(new BookingClass("Bus Stand, Rishikesh", "14/08/19", "3:15AM", "190RS", "54", "456", "Yadav"));
        arrayListBookings.add(new BookingClass("Bus Stand, Kotdwar", "12/12/23", "7:50AM", "175RS", "45", "123", "Badoni"));
        arrayListBookings.add(new BookingClass("Bus Stand, Rishikesh", "14/08/19", "3:15AM", "190RS", "54", "456", "Yadav"));
        arrayListBookings.add(new BookingClass("Bus Stand, Kotdwar", "12/12/23", "7:50AM", "175RS", "45", "123", "Badoni"));
        arrayListBookings.add(new BookingClass("Bus Stand, Rishikesh", "14/08/19", "3:15AM", "190RS", "54", "456", "Yadav"));

        recyclerView.setHasFixedSize(false);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), arrayListBookings);
        recyclerView.setAdapter(recyclerViewAdapter);

        //setting layout manager for recycler view
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.idMenuItemLogOut:
                mAuth.signOut();
                mUser = mAuth.getCurrentUser();
                updateUI(mUser);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}