package com.example.uncolor.fiveseconds;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Process;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    Button buttonStartGame;
    PullAnimator pullAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        buttonStartGame = (Button)findViewById(R.id.buttonStartGame);
        pullAnimator = new PullAnimator();
        pullAnimator.prepare(buttonStartGame);

    }

    public void onClickButtonStartGame(View view) {

        Intent intent = new Intent(MainMenuActivity.this, FillPlayersActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {

    }
}


