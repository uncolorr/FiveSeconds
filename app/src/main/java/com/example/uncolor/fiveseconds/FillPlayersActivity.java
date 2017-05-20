package com.example.uncolor.fiveseconds;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FillPlayersActivity extends AppCompatActivity {

    private static final int MAX_PLAYERS = 6;

    ListView listViewPlayers;
    Button buttonAddPlayer;
    EditText editTextAddPlayer;
    ArrayList<String> namesItems;
    ArrayList<Player> players;
    ListViewPlayersAdapter listViewPlayersAdapter;
    Button buttonNext;
    ImageView imageViewChangeColor;
    ShakeAnimator shakeAnimator;
    ScaleAnimator scaleAnimator;
    ScaleAnimator scaleAnimatorNext;
    GradientDrawable shape;
    int PlayerColorsCount;
    int currentPlayerColorIndex;
    ArrayList<String> colors;
    Random rnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_players);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        rnd = new Random(System.currentTimeMillis());
        editTextAddPlayer = (EditText)findViewById(R.id.textEditAddPlayer);
        buttonAddPlayer = (Button)findViewById(R.id.buttonAddPlayer);
        listViewPlayers = (ListView)findViewById(R.id.listViewPlayers);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        shape = new GradientDrawable();
        shape.setCornerRadius(250);
        imageViewChangeColor = (ImageView)findViewById(R.id.imageViewChangeColor);


        namesItems = new ArrayList<String>();
        colors = new ArrayList<String>();
        players = new ArrayList<Player>();
        initPlayerColors();
        shape.setColor(Color.parseColor(colors.get(0)));
        currentPlayerColorIndex = 0;
        imageViewChangeColor.setBackground(shape);
        scaleAnimator = new ScaleAnimator();
        scaleAnimator.prepare(buttonAddPlayer);
        scaleAnimator.setDuration(500);
        scaleAnimatorNext = new ScaleAnimator();
        scaleAnimatorNext.setDuration(500);
        shakeAnimator = new ShakeAnimator();

        listViewPlayersAdapter = new ListViewPlayersAdapter(this, players, colors);
        listViewPlayers.setAdapter(listViewPlayersAdapter);





    }
    public void onClickAddPlayer(View view) {

        if(editTextAddPlayer.getText().toString().isEmpty() || players.size() == MAX_PLAYERS)
        {
            scaleAnimator.prepare(buttonAddPlayer);
            shakeAnimator.prepare(editTextAddPlayer);
        }
        else
        {
           scaleAnimator.prepare(buttonAddPlayer);

            String name = editTextAddPlayer.getText().toString();
            View v = this.getCurrentFocus();
            if (v != null)
            {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            players.add(new Player(editTextAddPlayer.getText().toString(),0,colors.get(currentPlayerColorIndex), false));
            namesItems.add(editTextAddPlayer.getText().toString());//
            colors.remove(currentPlayerColorIndex);
            if(!colors.isEmpty())
            {
                shape.setColor(Color.parseColor(colors.get(0)));
                currentPlayerColorIndex = 0;
            }

            editTextAddPlayer.getText().clear();
            listViewPlayersAdapter.notifyDataSetChanged();
        }
    }

    public void onClickButtonNext(View view) {
        if(namesItems.isEmpty())
        {
            shakeAnimator.prepare(editTextAddPlayer);
            scaleAnimator.prepare(buttonNext);
        }
        else
        {
            scaleAnimator.prepare(buttonNext);
            Intent intent = new Intent(FillPlayersActivity.this, MainActivity.class);
            intent.putExtra("names", namesItems);
            intent.putParcelableArrayListExtra("xyz", players);
            startActivity(intent);
        }
    }

    public void onClickChangeColor(View view) {
        scaleAnimator.prepare(imageViewChangeColor);

        currentPlayerColorIndex = rnd.nextInt(colors.size());
        shape.setColor(Color.parseColor(colors.get(currentPlayerColorIndex)));




    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        Intent intent = new Intent(FillPlayersActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    private void initPlayerColors(){

        colors.add("#ff00ff");
        colors.add("#ff3300");
        colors.add("#333333");
        colors.add("#009900");
        colors.add("#3399CC");
        colors.add("#000099");

    }
}
