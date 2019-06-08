package com.example.busbookingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.busbookingapp.R;
import com.example.busbookingapp.core.BookingClass;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder> {

    ArrayList<BookingClass> arrayListBookings;
    Context context;

    public RecyclerViewAdapter(Context context, ArrayList<BookingClass> arrayListBookings) {
        this.arrayListBookings = arrayListBookings;
        this.context = context;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButtonBook;
        TextView textViewDate;
        TextView textViewTime;
        TextView textViewFare;
        TextView textViewSeats;
        TextView textViewDestination;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButtonBook = itemView.findViewById(R.id.idButtonBook);
            textViewDate = itemView.findViewById(R.id.idTextViewDate);
            textViewTime = itemView.findViewById(R.id.idTextViewTime);
            textViewSeats = itemView.findViewById(R.id.idTextViewSeats);
            textViewFare = itemView.findViewById(R.id.idTextViewFare);
            textViewDestination = itemView.findViewById(R.id.idTextViewDestination);
        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.booking_card, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder cardViewHolder, int i) {
        BookingClass bookingClass = arrayListBookings.get(i);
        cardViewHolder.textViewDate.setText(bookingClass.getDate());
        cardViewHolder.textViewTime.setText(bookingClass.getTime());
        cardViewHolder.textViewFare.setText(bookingClass.getFare());
        cardViewHolder.textViewSeats.setText(bookingClass.getSeatsFilled()+"/"+bookingClass.getTotalSeats());
        cardViewHolder.textViewDestination.setText(bookingClass.getDestination());
        cardViewHolder.imageButtonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewHolder.textViewSeats.setText("Full");
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListBookings.size();
    }


}
