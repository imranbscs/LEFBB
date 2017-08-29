package com.laeb.laebproject.team_fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.laeb.laebproject.adapter_team.AdapterInvitePlayer;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model.UpComingGames;
import com.laeb.laebproject.model_create_team.AllPlayers;
import com.laeb.laebproject.model_create_team.Datum;
import com.laeb.laebproject.model_create_team.SucessResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tariq on 8/28/2017.
 */

public class FragmentInvitePlayer extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Datum> listItems;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_invite_player, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        getAllplayers();




//        for(int i = 0; i < 10; i++){
//            listItems.add(new UpComingGames("RMD", "Riyadh", "Aug 16,2014 | 17:15"));
//        }


    }

    public void getAllplayers(){
        final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.169.138.14:4000/api/teams/listPlayer", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                AllPlayers sucessResponse = gson.fromJson(response, AllPlayers.class);
                int _status = sucessResponse.getStatus();

                listItems = new ArrayList<>();
                listItems = sucessResponse.getData();

                recyclerView = (RecyclerView) getView().findViewById(R.id.recylerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                Log.v("qwe", listItems.size()+"======"+listItems);

                Toast.makeText(getActivity(), "sucessful", Toast.LENGTH_LONG).show();

                adapter = new AdapterInvitePlayer(listItems, getActivity());
                recyclerView.setAdapter(adapter);


                if(_status == 200){

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
                headers.put("x-access-token", Prefs.getString(getActivity(), Prefs.auth_key));
                headers.put("locale", Globels.LOCAL);
                headers.put("Content-Type", Globels.CONTENT_TYPE);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };;

        requestQueue.add(stringRequest);
    }

}