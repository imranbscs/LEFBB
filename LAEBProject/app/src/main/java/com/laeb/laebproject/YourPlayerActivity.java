package com.laeb.laebproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.laeb.laebproject.team_fragments.FragmentYourPlayer;
import com.laeb.laebproject.team_fragments.FragmentYourTeam;

public class YourPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_player);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        FragmentYourPlayer fragmentsBooking = new FragmentYourPlayer();
        addFragment(fragmentsBooking);
    }

    public void addFragment(Fragment f) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, f);
        fragmentTransaction.commit();
    }
}