package com.laeb.laebproject.team_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laeb.laebproject.BookingActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.fragments_booking.FragmentSearchFacilities;

/**
 * Created by tariq on 8/26/2017.
 */

public class FragmentCreateTeam extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_createteam, container, false);
//        TextView searchTv = (TextView) v.findViewById(R.id.search_text);
//        searchTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentSearchFacilities fragment = new FragmentSearchFacilities();
//                ((BookingActivity) getActivity()).addFragment(fragment);
//            }
//        });
        return v;
    }
}