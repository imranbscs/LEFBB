package com.laeb.laebproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.laeb.laebproject.general.GlobelList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();
        GlobelList.getCities(this);
    }

    public void profileClicked(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }
    public void bookingClicked(View view) {
        startActivity(new Intent(this, BookingActivity.class));
    }

    public void addTeamClicked(View view) {
        //startActivity(new Intent(this, CreateTeamActivity.class));
        startActivity(new Intent(this, InvitePlayerActivity.class));
    }

    public void notificationClicked(View view) {
//        GlobelList.getCities(this);
    }
}
