package com.example.profilescreen;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView CATEGORY, NAME, RADIUS;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        CATEGORY=itemView.findViewById(R.id.CATEGORY);
        NAME=itemView.findViewById(R.id.NAME);
        RADIUS=itemView.findViewById(R.id.RADIUS);

    }
}
