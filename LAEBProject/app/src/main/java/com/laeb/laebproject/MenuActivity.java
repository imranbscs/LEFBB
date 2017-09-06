package com.laeb.laebproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.laeb.laebproject.fragment.FragmentFootballFields;
import com.laeb.laebproject.fragment.FragmentProfile;
import com.laeb.laebproject.fragment.FragmentScheduleRef;
import com.laeb.laebproject.fragment.FragmentUpComingEvents;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        getSupportActionBar().hide();
//        FragmentProfile fragmentProfile = new FragmentProfile();
//        addFragment(fragmentProfile);
    }

    public void userProfile(View view) {
        Fragment myfragment = new FragmentProfile();
        addFragment(myfragment);
    }

    public void profileClicked(View view) {
        Fragment myfragment = new FragmentUpComingEvents();
        addFragment(myfragment);
    }

    void addFragment(Fragment f){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, f);
        fragmentTransaction.commit();
    }


    public void homeClicked(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void footBallFieldClicked(View view) {
        Fragment myfragment = new FragmentFootballFields();
        addFragment(myfragment);
    }

    public void refreeClicked(View view) {
        Fragment myfragment = new FragmentScheduleRef();
        addFragment(myfragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentProfile fragmentProfile = new FragmentProfile();
        addFragment(fragmentProfile);
    }
}
