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
import android.widget.RelativeLayout;
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
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model_create_team.team_schedule.Datum;
import com.laeb.laebproject.model_create_team.team_schedule.TeamSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tariq on 9/11/2017.
 */

public class FragmentSubSchedule extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Datum> scheduleItems;
    TextView texInvi, txtMale;
    RelativeLayout male_selector, female_delector1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sub_schedule, container, false);
//        ((YourTeamActivity) getActivity()).title.setText("YOUR PLAYERS");
//        texInvi = (TextView) v.findViewById(R.id.texInvi);
//        male_selector = (RelativeLayout) v.findViewById(R.id.male_selector);
//        female_delector1  = (RelativeLayout) v.findViewById(R.id.female_delector);
//        txtMale = (TextView) v.findViewById(R.id.txtMale);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getAllplayers();
    }

    public void getAllplayers(){
        final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.169.138.14:4000/api/match/userScheduledMatches", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                TeamSchedule sucessResponse = gson.fromJson(response, TeamSchedule.class);
                int _status = sucessResponse.getStatus();

                Toast.makeText(getActivity(), "sucess", Toast.LENGTH_SHORT).show();
                scheduleItems = new ArrayList<>();
                scheduleItems = sucessResponse.getData();

                if(_status == 200){
                    recyclerView = (RecyclerView) getView().findViewById(R.id.recylerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    Log.v("qwe", scheduleItems.size()+"======"+scheduleItems);

                    Toast.makeText(getActivity(), "sucessful", Toast.LENGTH_LONG).show();

                    adapter = new AdapterSchedule(scheduleItems, getActivity());
                    recyclerView.setAdapter(adapter);
                }else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.v("wsx", "========   "+error+"");
                Toast.makeText(getActivity(), "Unable to connect...", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-access-key", Globels.ACCESS_KEY);
                //headers.put("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo1NywidXNlcm5hbWUiOiIwNTU1NTUxMDA1IiwiaWF0IjoxNTAyMTE4MDg0fQ.XHLt8e3AMioTxI8m0m3QEzvm3qUYixi9Bnr-XBztGXo");
                headers.put("x-access-token", Prefs.getString(getActivity(), Prefs.auth_key));
                headers.put("locale", Globels.LOCAL);
                headers.put("Content-Type", Globels.CONTENT_TYPE);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("team_id", "22");
                return params;
            }
        };;

        requestQueue.add(stringRequest);
    }

}
