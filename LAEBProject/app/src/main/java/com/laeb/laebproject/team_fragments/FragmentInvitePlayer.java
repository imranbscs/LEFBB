package com.laeb.laebproject.team_fragments;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
import com.laeb.laebproject.adapter_team.AdapterInvitePlayer;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model.UpComingGames;
import com.laeb.laebproject.model_create_team.AllPlayers;
import com.laeb.laebproject.model_create_team.Datum;
import com.laeb.laebproject.model_create_team.SucessResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tariq on 8/28/2017.
 */

public class FragmentInvitePlayer extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Datum> listItems;
    LinearLayout doneBtn;
    ImageView sort;
    ImageView filter;
    String listofPlayer = "";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_invite_player, container, false);
        doneBtn = (LinearLayout) v.findViewById(R.id.doneBtnInvite);
        sort = (ImageView) v.findViewById(R.id.sort);
        filter = (ImageView) v.findViewById(R.id.search);
        doneBtn.setOnClickListener(this);
        sort.setOnClickListener(this);
        filter.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.doneBtnInvite:
                listofPlayer = AdapterInvitePlayer.doneInvitation();
                sendInvitation();
                break;
            case R.id.sort:
                sort();
                break;
            case R.id.search:
                searchList();
                break;
        }
    }

    private void searchList() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_search_player);
        //dialog.setTitle(R.string.pickup);
        dialog.show();
        TextView okBtn = (TextView) dialog.findViewById(R.id.done);
        TextView cencelBtn = (TextView) dialog.findViewById(R.id.cencel);
        final EditText player = (EditText) dialog.findViewById(R.id.rate);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchApiCalled(player.getText().toString());
                dialog.dismiss();
            }
        });

        cencelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void sort() {
        Collections.sort(listItems, new Comparator<Datum>() {
            @Override
            public int compare(Datum lhs, Datum rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sendInvitation() {

        if(listofPlayer.equals("")){
            return;
        }

        final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/teams/invitePlayers", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                SucessResponse sucessResponse = gson.fromJson(response, SucessResponse.class);
                String _message = sucessResponse.messege;
                int _status = sucessResponse.status;
                Toast.makeText(getActivity(), "sucessful====  "+_status, Toast.LENGTH_LONG).show();
                if(_status == 200){
                    //startActivity(new Intent(getActivity(), InvitePlayerActivity.class));
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
                params.put("player", listofPlayer);
                return params;
            }
        };;

        requestQueue.add(stringRequest);
    }

    public void searchApiCalled(String player){

        final String mPlayer = player;

        final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/teams/listPlayer", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("ghg", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                AllPlayers sucessResponse = gson.fromJson(response, AllPlayers.class);
                int _status = sucessResponse.getStatus();

                List listItems = new ArrayList<>();
                listItems = sucessResponse.getData();

                adapter = new AdapterInvitePlayer(listItems, getActivity());
                recyclerView.setAdapter(adapter);


                if(_status == 200){
                    //startActivity(new Intent(getActivity(), InvitePlayerActivity.class));
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
                params.put("name", mPlayer);
                params.put("city", "");
                params.put("playing_role", "");
                return params;
            }
        };;

        requestQueue.add(stringRequest);
    }

}