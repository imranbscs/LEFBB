package com.laeb.laebproject.team_fragments;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.laeb.laebproject.InvitePlayerActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.YourTeamActivity;

/**
 * Created by tariq on 8/27/2017.
 */

public class FragmentYourTeam extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_your_team, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.flag1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InvitePlayerActivity.class));
            }
        });
        return v;
    }

}