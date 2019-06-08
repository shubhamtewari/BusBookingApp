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
import com.example.busbookingapp.core.BookingClass;
import com.example.busbookingapp.viewmodel.BookingsViewModel;

import java.util.ArrayList;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ListViewHolder> {

    ArrayList<BookingClass> arrayListBookings;
    Context context;
    BookingsViewModel model;

    public ListRecyclerViewAdapter(Context context, ArrayList<BookingClass> arrayListBookings) {
        this.arrayListBookings = arrayListBookings;
        this.context = context;
        AppCompatActivity activity = (AppCompatActivity) context;
        model = ViewModelProviders.of(activity).get(BookingsViewModel.class);
    }

    public void updateData(ArrayList<BookingClass> bookingClasses) {
        arrayListBookings = bookingClasses;
        notifyDataSetChanged();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInfo;
        Button buttonDelete;
        Button buttonDetails;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInfo = itemView.findViewById(R.id.idTextViewInfo);
            buttonDelete = itemView.findViewById(R.id.idButtonDelete);
            buttonDetails = itemView.findViewById(R.id.idButtonDetails);
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
        final BookingClass bookingClass = arrayListBookings.get(i);
        listViewHolder.textViewInfo.setText(bookingClass.getDestination()+":"+bookingClass.getDate()+":"+bookingClass.getTime());
        listViewHolder.buttonDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.deleteBooking(i);
                return true;
            }
        });

        listViewHolder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.selectBooking(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListBookings.size();
    }


}
