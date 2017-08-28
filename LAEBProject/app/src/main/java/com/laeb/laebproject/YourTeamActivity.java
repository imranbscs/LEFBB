package com.laeb.laebproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.laeb.laebproject.team_fragments.FragmentCreateTeam;
import com.laeb.laebproject.team_fragments.FragmentYourPlayer;
import com.laeb.laebproject.team_fragments.FragmentYourTeam;

public class YourTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_team);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        FragmentYourTeam fragmentsBooking = new FragmentYourTeam();
        addFragment(fragmentsBooking);
    }

    public void addFragment(Fragment f) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, f);
        fragmentTransaction.commit();
    }

    public void multiPlayer(View view) {

        FragmentYourPlayer fragmentsBooking = new FragmentYourPlayer();
        addFragment(fragmentsBooking);

    }
}