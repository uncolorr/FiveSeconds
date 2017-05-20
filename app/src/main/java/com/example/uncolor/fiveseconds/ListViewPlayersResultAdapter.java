package com.example.uncolor.fiveseconds;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uncolor on 20.05.17.
 */

public class ListViewPlayersResultAdapter extends BaseAdapter {

    ArrayList<Player> players = new ArrayList<Player>();
    private static final String COLOR_GOLD = "#ffd700";
    private static final String COLOR_SILVER = "#c0c0c0";
    private static final String COLOR_BRONZE = "#cd7f32";
    private static final String COLOR_OTHER_PLACE = "#1e90ff";



    LayoutInflater layoutInflater;
    public ListViewPlayersResultAdapter(Context context, ArrayList<Player> players){
        layoutInflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.players = players;

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
            view = layoutInflater.inflate(R.layout.list_view_player_item_result, parent, false);
        }

        TextView textViewNamePlayerResult = (TextView)view.findViewById(R.id.textViewPlayerNameResult);
        textViewNamePlayerResult.setText(players.get(position).name());

        ImageView imageViewPlayerPlace = (ImageView)view.findViewById(R.id.imageViewPlayerPlace);
        GradientDrawable shapePlace = new GradientDrawable();
        switch (position) {
            case 0:
                shapePlace.setColor(Color.parseColor(COLOR_GOLD));
                break;
            case 1:
                shapePlace.setColor(Color.parseColor(COLOR_SILVER));
                break;
            case 2:
                shapePlace.setColor(Color.parseColor(COLOR_BRONZE));
                break;
            default:
                shapePlace.setColor(Color.parseColor(COLOR_OTHER_PLACE));

        }
        shapePlace.setCornerRadius(250);
        imageViewPlayerPlace.setBackground(shapePlace);

        ImageView imageViewPlayerColorResult = (ImageView)view.findViewById(R.id.imageViewPlayerColorResult);
        GradientDrawable shapeColor = new GradientDrawable();
        shapeColor.setColor(Color.parseColor(players.get(position).color()));
        shapeColor.setCornerRadius(250);
        imageViewPlayerColorResult.setBackground(shapeColor);

        TextView textViewPlayerPlace = (TextView)view.findViewById(R.id.textViewPlayerPlace);
        textViewPlayerPlace.setText(Integer.toString(position + 1));

        TextView textViewPlayerPointResults = (TextView)view.findViewById(R.id.textViewPlayerPointResults);
        textViewPlayerPointResults.setText(Integer.toString(players.get(position).points()));





        return view;
    }
}
