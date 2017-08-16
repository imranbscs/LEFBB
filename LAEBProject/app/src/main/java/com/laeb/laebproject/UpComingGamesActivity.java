package com.laeb.laebproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UpComingGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_games);
        getSupportActionBar().hide();
    }
}
