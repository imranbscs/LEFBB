package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;

/**
 * Created by tariq on 8/18/2017.
 */

public class MapFragment extends Fragment implements View.OnClickListener {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        View b = (View) view.findViewById(R.id.nextMapp);
        b.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextMapp:
                SoicalMediaFragment fragment = new SoicalMediaFragment();
                ((CreateFieldActivity)getActivity()).addFragment(fragment);
                break;
        }
    }
}