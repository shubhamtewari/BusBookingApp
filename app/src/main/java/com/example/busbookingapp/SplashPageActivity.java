package com.example.busbookingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SplashPageActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    Button buttonUser;
    ImageView buttonAdmin;



    //login fragment interaction methods ------------------>
    //hide user and admin button if fragment is not visible, b = true
    @Override
    public void onFragmentNotVisible(boolean b) {
        if(b) {
            buttonUser.setVisibility(View.VISIBLE);
        }
        else {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    buttonUser.setVisibility(View.INVISIBLE);
                }
            });
            t.start();

        }
    }

    //destroy the activity, call onDestroy()
    @Override
    public void onSucessfullSignin() {
        finish();
    }
    //login fragment interaction methods <-------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        buttonAdmin = findViewById(R.id.idAdminButton);

        buttonUser = findViewById(R.id.idButtonUser);

        //open admin account
        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(i);
                //finish();

            }
        });

        //open login fragment
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LoginFragment loginFragment = LoginFragment.newInstance("aaaaaaaa");
                fragmentTransaction.setCustomAnimations(R.anim.slide_up_and_deaccelerate, R.anim.slide_right, R.anim.slide_right, R.anim.slidedown_and_accelerate);
                fragmentTransaction.add(R.id.idFrameLayout, loginFragment, "LOGIN_FRAGMENT");
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });


    }
}
