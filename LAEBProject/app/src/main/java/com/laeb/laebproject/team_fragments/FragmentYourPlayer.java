package com.laeb.laebproject.team_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laeb.laebproject.R;
import com.laeb.laebproject.adapter_team.AdapterInvitePlayer;
import com.laeb.laebproject.adapter_team.AdapterYourPlayer;
import com.laeb.laebproject.model.UpComingGames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariq on 8/28/2017.
 */

public class FragmentYourPlayer extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<UpComingGames> listItems;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_your_player, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listItems = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            listItems.add(new UpComingGames("RMD", "Riyadh", "Aug 16,2014 | 17:15"));
        }

        adapter = new AdapterYourPlayer(listItems, getActivity());
        recyclerView.setAdapter(adapter);
    }

}