package com.example.busbookingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.busbookingapp.repository.DatabaseManipulation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private static final String TEST = "param1";

    //private DatabaseConnection firebaseDatabaseConnection;
    private DatabaseManipulation firebaseDatabase;

    private FirebaseAuth mAuth;

    private String mParam1;

    private OnFragmentInteractionListener mListener;

    private TextView textViewWelcome;
    private TextView textViewLogin;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    private TextView textViewNotAUser;
    private TextView textViewSignUp;

    public LoginFragment() {

    }

    public void updateUI(boolean b) {
        if(b) {
            Intent i = new Intent(getContext(), MainActivity.class);
            startActivity(i);
            mListener.onSucessfullSignin();
        }
    }

    //firebase methods ------------------->
    public void signInUser(String name, String password) throws Exception {
        mAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    updateUI(true);
                } else {
                    Toast.makeText(getContext(), "Error Signing In."+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //firebase methods <--------------------

    /**
     * to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter .
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance(String param1) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(TEST, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(TEST);
        }
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        //initializing the views
        editTextEmail = v.findViewById(R.id.idEditTextEmail);
        editTextPassword = v.findViewById(R.id.idEditTextPassword);
        textViewNotAUser = v.findViewById(R.id.idTextViewInfo);
        textViewSignUp = v.findViewById(R.id.idTextViewSignUp);
        buttonLogin = v.findViewById(R.id.idButtonSignIn);

        editTextEmail.setText("shbmtewari@gmail.com");
        editTextEmail.requestFocus();

        // Inflate the layout for this fragment
        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.onFragmentNotVisible(false);

        editTextPassword.setText(mParam1);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signInUser(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(getContext(), "Error Signing In.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mListener.onFragmentNotVisible(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onFragmentNotVisible(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mListener.onFragmentNotVisible(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        mListener.onFragmentNotVisible(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentNotVisible(boolean b);
        void onSucessfullSignin();
    }
}
