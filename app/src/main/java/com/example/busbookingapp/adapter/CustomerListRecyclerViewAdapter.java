package com.example.busbookingapp.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.busbookingapp.R;
import com.example.busbookingapp.core.CustomerBookingClass;
import com.example.busbookingapp.viewmodel.BookingsViewModel;

import java.util.ArrayList;

public class CustomerListRecyclerViewAdapter extends RecyclerView.Adapter<CustomerListRecyclerViewAdapter.ListViewHolder> {

    ArrayList<String> arrayListCustomers;
    Context context;
    BookingsViewModel model;

    public CustomerListRecyclerViewAdapter(Context context, ArrayList<String> arrayListCustomers) {
        this.arrayListCustomers = arrayListCustomers;
        this.context = context;
    }

    public void updateData(ArrayList<String> customers) {
        if(customers==null) {
            arrayListCustomers.clear();
        }
        arrayListCustomers = customers;
        notifyDataSetChanged();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInfo;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInfo = itemView.findViewById(R.id.idTextViewInfo);
        }
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.listitem_layout, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int i) {
        final String customer = arrayListCustomers.get(i);
        AppCompatActivity activity = (AppCompatActivity) context;
        model = ViewModelProviders.of(activity).get(BookingsViewModel.class);
        listViewHolder.textViewInfo.setText(customer);
    }

    @Override
    public int getItemCount() {
        return arrayListCustomers.size();
    }


}
