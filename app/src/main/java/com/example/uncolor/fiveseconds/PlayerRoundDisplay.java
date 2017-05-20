package com.example.uncolor.fiveseconds;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by uncolor on 17.05.17.
 */

public class PlayerRoundDisplay extends RecyclerView.Adapter<PlayerViewHolder> {

    ArrayList<Player> players = new ArrayList<Player>();

    public PlayerRoundDisplay(ArrayList<Player> players){

        this.players = players;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_round_player, parent, false);
        PlayerViewHolder holder = new PlayerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.textViewPoints.setText(Integer.toString(players.get(position).points()));
        holder.imageViewRoundBackground.setImageResource(R.drawable.round_no_selected);
        Random rnd = new Random();
        int k = rnd.nextInt(2);
        if(players.get(position).isSelected())
        {
            holder.imageViewRoundBackground.setImageResource(R.drawable.round_corner_start);
        }

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(250);
        shape.setColor(Color.parseColor(players.get(position).color()));
        holder.imageViewPlayerColor.setBackground(shape);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return players.size();
    }


}
