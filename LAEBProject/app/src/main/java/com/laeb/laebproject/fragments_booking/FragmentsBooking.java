package com.laeb.laebproject.fragments_booking;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laeb.laebproject.BookingActivity;
import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.adapters.SearchFacilitiesAdapter;
import com.laeb.laebproject.adapters.UpComingGamesAdapter;
import com.laeb.laebproject.create_field_fragments.SoicalMediaFragment;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;
import com.laeb.laebproject.model.UpComingGames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariq on 8/26/2017.
 */

public class FragmentsBooking extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.booking_animation, container, false);
        TextView searchTv = (TextView) v.findViewById(R.id.search_text);
        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentSearchFacilities fragment = new FragmentSearchFacilities();
                ((BookingActivity) getActivity()).addFragment(fragment);
            }
        });
        return v;
    }
}