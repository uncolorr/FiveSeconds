package com.example.uncolor.fiveseconds;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    ArrayList<Player> players = new ArrayList<Player>();
    ListView listViewResults;
    Button buttonFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        listViewResults = (ListView) findViewById(R.id.listViewResults);
        buttonFinish = (Button) findViewById(R.id.buttonFinish);
        players = getIntent().getParcelableArrayListExtra("players");
        ListViewPlayersResultAdapter listViewPlayersResultAdapter = new ListViewPlayersResultAdapter(this, players);
        listViewResults.setAdapter(listViewPlayersResultAdapter);
    }

    public void onClickButtonFinish(View view){
        finishAffinity();
        Intent intent = new Intent(ResultsActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
