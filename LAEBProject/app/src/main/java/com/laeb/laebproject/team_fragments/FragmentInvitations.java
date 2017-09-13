package com.laeb.laebproject.team_fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.laeb.laebproject.R;
import com.laeb.laebproject.YourTeamActivity;
import com.laeb.laebproject.adapter_team.AdapterSchedule;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.model_create_team.team_schedule.Datum;
import com.laeb.laebproject.model_create_team.team_schedule.TeamSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tariq on 9/11/2017.
 */

public class FragmentInvitations extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Datum> scheduleItems;
    TextView texInvi;
    ImageView imgSent;
    ImageView imgRecived;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_invitations_team, container, false);
        ((YourTeamActivity) getActivity()).title.setText("YOUR PLAYERS");
        TextView sent = (TextView) v.findViewById(R.id.sentTv);
        TextView recived = (TextView) v.findViewById(R.id.recivedTv);
        imgSent = (ImageView) v.findViewById(R.id.sentImg);
        imgRecived = (ImageView) v.findViewById(R.id.recivedImg);

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentSabSent f = new FragmentSabSent();
                addFragment(f);
                imgSent.setImageResource(R.drawable.tickselected);
                imgRecived.setImageResource(R.drawable.tick);
            }
        });

        recived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentSabRecived f = new FragmentSabRecived();
                addFragment(f);
                imgRecived.setImageResource(R.drawable.tickselected);
                imgSent.setImageResource(R.drawable.tick);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void addFragment(Fragment f) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.f_layout, f);
        fragmentTransaction.commit();
    }
}
