package com.example.uncolor.fiveseconds;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static int MAX_ROUNDS = 10;

    Button buttonYes;
    Button buttonNo;
    TextView textView;
    TextView textViewQuestion;
    TextView textViewPaper;
    TextView textViewCurrentPlayerName;
    Button buttonStart;
    ImageView imageViewCurrentPlayer;
    RecyclerView recyclerView;
    ArrayList<String> dataQuestions;
    ScaleAnimator scaleAnimator;
    FadeInAnimator fadeInAnimator;
    FadeOutAnimator fadeOutAnimator;
    ShakeAnimator shakeAnimator;
    boolean isQuestionHiding;
    int currentPlayer;
    int firstAnsweringPlayer;
    ArrayList<String> names;
    ArrayList<Player> players;
    PlayerRoundDisplay playerRoundDisplay;
    Random rnd;
    GradientDrawable shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        names = new ArrayList<String>();

        names = getIntent().getStringArrayListExtra("names");
        players =  new ArrayList<Player>();
        players = getIntent().getParcelableArrayListExtra("xyz");
        currentPlayer = 0;
        players.get(currentPlayer).setSelected(true);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(this);
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        playerRoundDisplay = new PlayerRoundDisplay(players);
        recyclerView.setAdapter(playerRoundDisplay);
        recyclerView.setLayoutManager(MyLayoutManager);

        buttonYes = (Button)findViewById(R.id.buttonYes);
        buttonNo = (Button)findViewById(R.id.buttonNo);
        buttonStart = (Button)findViewById(R.id.buttonStart);
        textView = (TextView)findViewById(R.id.textView);
        textViewQuestion = (TextView)findViewById(R.id.textViewQuestion);
        textViewPaper = (TextView)findViewById(R.id.textViewPaper);
        textViewCurrentPlayerName = (TextView)findViewById(R.id.textViewCurrentPlayerName);
        imageViewCurrentPlayer = (ImageView)findViewById(R.id.imageViewCurrentPlayer);
        scaleAnimator = new ScaleAnimator();
        fadeInAnimator = new FadeInAnimator();
        fadeOutAnimator = new FadeOutAnimator();
        shakeAnimator = new ShakeAnimator();
        dataQuestions = new ArrayList<String>();
        shape = new GradientDrawable();
        shape.setCornerRadius(250);
        imageViewCurrentPlayer.setBackground(shape);
        isQuestionHiding = false;
        rnd = new Random(System.currentTimeMillis());

        buttonYes.setEnabled(false);
        buttonYes.setAlpha(0.0f);
        buttonNo.setEnabled(false);
        buttonNo.setAlpha(0.0f);

        try
        {

            Resources res = getResources();
            InputStream inputStream = res.openRawResource(R.raw.five_seconds);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            String string = new String(b);
            String[] tempData = string.split("\\n");
            for(int i = 0; i < tempData.length; i++)
            {
                dataQuestions.add(tempData[i]);
            }

        } catch (Exception e) {

        }

        Random rnd = new Random(System.currentTimeMillis());
        textViewQuestion.setText(dataQuestions.get(rnd.nextInt(dataQuestions.size() - 1)));

        textViewCurrentPlayerName.setText("Отвечает " + players.get(currentPlayer).name());
        shape.setColor(Color.parseColor(players.get(currentPlayer).color()));



   }

    @Override
    public void onBackPressed() {

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.textViewAlertDialog);
        text.setText("Хотите завершить текущую игру?");
        Button buttonAlertYes = (Button) dialog.findViewById(R.id.buttonAlertYes);
        Button buttonAlertNo = (Button) dialog.findViewById(R.id.buttonAlertNo);

        buttonAlertYes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finishAffinity();
                Intent intent = new Intent(MainActivity.this,MainMenuActivity.class);
                startActivity(intent);

            }
        });

        buttonAlertNo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    public void onClickYes(View view){

        scaleAnimator.prepare(buttonYes);
        fadeOutAnimator.prepare(buttonYes);
        fadeOutAnimator.prepare(buttonNo);
        textView.setText("...");
        textViewPaper.setEnabled(true);
        buttonStart.setEnabled(true);
        buttonYes.setEnabled(false);
        buttonNo.setEnabled(false);
        textViewQuestion.setText(dataQuestions.get(rnd.nextInt(dataQuestions.size() - 1)));
        players.get(currentPlayer).addPoint();
        players.get(currentPlayer).setSelected(false);
        playerRoundDisplay.notifyDataSetChanged();

        if(isGameOver())
        {
            gameOver();
        }
        nextPlayer();
        players.get(currentPlayer).setSelected(true);

    }

    public void onClickNo(View view){

        players.get(currentPlayer).setSelected(false);
        nextPlayer();
        players.get(currentPlayer).setSelected(true);
        playerRoundDisplay.notifyDataSetChanged();
        if(currentPlayer == firstAnsweringPlayer)
        {
            players.get(firstAnsweringPlayer).addPoint();
            players.get(currentPlayer).setSelected(false);
            nextPlayer();
            players.get(currentPlayer).setSelected(true);
            playerRoundDisplay.notifyDataSetChanged();
            fadeOutAnimator.prepare(buttonYes);
            fadeOutAnimator.prepare(buttonNo);
            textView.setText("...");
            textViewPaper.setEnabled(true);
            buttonStart.setEnabled(true);
            buttonYes.setEnabled(false);
            buttonNo.setEnabled(false);
            textViewQuestion.setText(dataQuestions.get(rnd.nextInt(dataQuestions.size() - 1)));
            if(isGameOver())
            {
                gameOver();
            }
        }
        else {

            scaleAnimator.prepare(buttonNo);

            fadeOutAnimator.prepare(buttonYes);
            fadeOutAnimator.prepare(buttonNo);

            buttonYes.setEnabled(false);
            buttonNo.setEnabled(false);


            new CountDownTimer(5000, 100) {
                @Override
                public void onTick(long millisUntilFinished) {

                    Double temp = millisUntilFinished * 0.001;
                    String stringTimer = Double.toString(temp);
                    stringTimer = stringTimer.substring(0, 3);
                    textView.setText(stringTimer);
                    if (temp <= 1.0 && !isQuestionHiding) {
                        fadeOutAnimator.prepare(textViewQuestion);
                        isQuestionHiding = true;
                    }

                }

                @Override
                public void onFinish() {

                    textView.setText("Игрок ответил на вопрос?");
                    buttonYes.setEnabled(true);
                    buttonNo.setEnabled(true);

                    fadeInAnimator.prepare(buttonYes);
                    fadeInAnimator.prepare(buttonNo);
                    fadeInAnimator.prepare(textViewQuestion);

                    isQuestionHiding = false;

                }
            }.start();
        }

    }




    public void onClickPaper(View view){

        scaleAnimator.prepare(textViewQuestion);
        textViewQuestion.setText(dataQuestions.get(rnd.nextInt(dataQuestions.size() - 1)));

    }

    public void onClickStart(View view) {

        scaleAnimator.prepare(buttonStart);
        buttonStart.setEnabled(false);
        buttonYes.setEnabled(false);
        buttonNo.setEnabled(false);
        textViewPaper.setEnabled(false);
        firstAnsweringPlayer = currentPlayer;

        new CountDownTimer(5000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

                Double temp = millisUntilFinished * 0.001;
                String stringTimer = Double.toString(temp);
                stringTimer = stringTimer.substring(0,3);
                textView.setText(stringTimer);
                if(temp <= 1.5 && !isQuestionHiding)
                {
                    fadeOutAnimator.prepare(textViewQuestion);
                    isQuestionHiding = true;
                }

            }

            @Override
            public void onFinish() {

                textView.setText("Игрок ответил на вопрос?");
                buttonYes.setEnabled(true);
                buttonNo.setEnabled(true);

                fadeInAnimator.prepare(buttonYes);
                fadeInAnimator.prepare(buttonNo);
                fadeInAnimator.prepare(textViewQuestion);

                isQuestionHiding = false;

            }
        }.start();

    }

    private void nextPlayer(){

        if(currentPlayer + 1 == players.size())
        {
            currentPlayer = 0;
        }
        else
        {
            currentPlayer++;
        }
        textViewCurrentPlayerName.setText("Отвечает " + players.get(currentPlayer).name());
        shape.setColor(Color.parseColor(players.get(currentPlayer).color()));



    }

    private boolean isGameOver() {
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).points() == MAX_ROUNDS)
            {
                return true;
            }
        }
        return false;
    }

    private void gameOver(){
        finishAffinity();
        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        intent.putParcelableArrayListExtra("players", players);
        startActivity(intent);
    }






}
