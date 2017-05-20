package com.example.uncolor.fiveseconds;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by uncolor on 17.05.17.
 */

public class PlayerViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageViewRoundBackground;
    public ImageView imageViewPlayerColor;
    TextView textViewPoints;


    public PlayerViewHolder(View itemView) {
        super(itemView);

        imageViewRoundBackground = (ImageView)itemView.findViewById(R.id.imageViewRoundBackground);
        imageViewPlayerColor = (ImageView)itemView.findViewById(R.id.imageViewColor);
        textViewPoints = (TextView)itemView.findViewById(R.id.textViewPoints);
    }


}
