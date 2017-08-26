package com.laeb.laebproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.laeb.laebproject.create_field_fragments.FragmentCreateField;
import com.laeb.laebproject.fragments_booking.FragmentSearchFacilities;
import com.laeb.laebproject.fragments_booking.FragmentsBooking;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        FragmentSearchFacilities fragmentsBooking = new FragmentSearchFacilities();
        addFragment(fragmentsBooking);
    }

    public void addFragment(Fragment f){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, f);
        fragmentTransaction.commit();
    }
}
