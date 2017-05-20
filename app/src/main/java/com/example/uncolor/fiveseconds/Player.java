package com.example.uncolor.fiveseconds;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by uncolor on 17.05.17.
 */

public class Player implements Parcelable {
    private int points;
    private String name;
    private String color;
    private boolean isSelected;





    public Player(String name, int points, String color, boolean isSelected) {

        this.name = name;
        this.points = points;
        this.color = color;
        this.isSelected = isSelected;

    }



    protected Player(Parcel in) {
        points = in.readInt();
        name = in.readString();
        color = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public void addPoint(){
        this.points++;
    }
    public String name(){
        return this.name;
    }
    public int points(){
        return this.points;
    }
    public String color(){return this.color;}
    public boolean isSelected(){ return this.isSelected;}
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(points);
        dest.writeString(name);
        dest.writeString(color);
    }
}
