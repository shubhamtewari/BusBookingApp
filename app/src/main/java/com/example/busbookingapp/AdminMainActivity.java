package com.example.busbookingapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        //loading fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AdminMainFragment adminMainFragment = AdminMainFragment.newInstance();
        fragmentTransaction.add(R.id.idFrameLayout, adminMainFragment, "ADMIN_MAIN_FRAGMENT");
        fragmentTransaction.commit();
    }
}
