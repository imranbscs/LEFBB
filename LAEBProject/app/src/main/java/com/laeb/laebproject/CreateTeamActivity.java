package com.laeb.laebproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.laeb.laebproject.team_fragments.FragmentCreateTeam;

public class CreateTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        FragmentCreateTeam fragmentsBooking= new FragmentCreateTeam();
        addFragment(fragmentsBooking);
    }

    public void addFragment(Fragment f){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, f);
        fragmentTransaction.commit();
    }
}
