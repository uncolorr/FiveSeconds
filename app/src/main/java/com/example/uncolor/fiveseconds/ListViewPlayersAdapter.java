package com.example.uncolor.fiveseconds;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uncolor on 13.05.17.
 */

public class ListViewPlayersAdapter extends BaseAdapter {

    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<String> colors = new ArrayList<String>();


    LayoutInflater layoutInflater;
    public ListViewPlayersAdapter(Context context, ArrayList<Player> players, ArrayList<String> colors){
        layoutInflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.players = players;
        this.colors = colors;
    }
    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null)
        {
            view = layoutInflater.inflate(R.layout.listview_player_item, parent, false);
        }

        TextView textViewName = (TextView)view.findViewById(R.id.textViewName);
        textViewName.setText(players.get(position).name());
        ImageView imageViewPlayerColor = (ImageView)view.findViewById(R.id.imageViewPlayerColor);
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(Color.parseColor(players.get(position).color()));
        shape.setCornerRadius(250);
        imageViewPlayerColor.setBackground(shape);
        ImageButton deleteItemButton = (ImageButton)view.findViewById(R.id.deleteItemButton);

        deleteItemButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

                colors.add(players.get(position).color());
                players.remove(position);
                notifyDataSetChanged();
            }

        });


        return view;
    }
}
