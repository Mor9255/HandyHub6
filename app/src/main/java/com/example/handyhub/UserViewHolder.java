package com.example.handyhub;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder  {

    public CardView cardView;
    public ImageView barbara;
    public TextView barbara_name;
    public TextView barbara_email;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardview);
        barbara = itemView.findViewById(R.id.barbara);
        barbara_name = itemView.findViewById(R.id.barbara_name);
        barbara_email = itemView.findViewById(R.id.barbara_email);

    }
}
