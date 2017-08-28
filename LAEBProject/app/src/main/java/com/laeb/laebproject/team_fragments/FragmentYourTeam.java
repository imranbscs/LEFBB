package com.laeb.laebproject.team_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laeb.laebproject.R;

/**
 * Created by tariq on 8/27/2017.
 */

public class FragmentYourTeam extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_your_team, container, false);
        return v;
    }

}