package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
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
import com.laeb.laebproject.MenuActivity;
import com.laeb.laebproject.ProfileActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.expendible_list.CustomExpandableListAdapter;
import com.laeb.laebproject.expendible_list.ExpandableListDataPump;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;
import com.laeb.laebproject.model.ImageList;
import com.laeb.laebproject.model_create_team.AllPlayers;
import com.laeb.laebproject.testjson.TestStaticMethod;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by tariq on 8/21/2017.
 */

public class WeeklyScheduleFragment extends Fragment implements View.OnClickListener {


    ExpandableListView expandableListView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    CustomBinder oCustom;
    FieldInfo fieldInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.field_weekly_schedule, container, false);

        oCustom = (CustomBinder) getArguments().get("complexObject");
        fieldInfo = oCustom.getField();
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);

        TextView aaa = (TextView) view.findViewById(R.id.ccc);
        aaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hello " + expandableListAdapter.expandableListDetail, Toast.LENGTH_SHORT).show();
            }
        });

        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        View footerView = View.inflate(getActivity(), R.layout.btn_footer, null);
        View b = (View) footerView.findViewById(R.id.btnNext);
        b.setOnClickListener(this);
        expandableListView.addFooterView(footerView);

        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:

                Gson gson = new Gson();
                String json = gson.toJson(TestStaticMethod.getAll());
                Log.v("ppp", "====== " + json);
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

                ImageList imageList = new ImageList();
                imageList.setImage(Globels.CREATE_FIELD_IMAGE);
                List<ImageList> imageLists = new ArrayList<>();
                imageLists.add(imageList);
                Gson gsonImage = new Gson();
                String imgListString = gson.toJson(imageLists);
                final String imgStr = imgListString;

                fieldInfo.nearby = "E11";
                String s = gson.toJson(fieldInfo);

                final String jasonn = json;

                Gson g = new Gson();
                json = g.toJson(TestStaticMethod.getAll());
                fieldInfo.nearby = "E11";

                String ss = g.toJson(fieldInfo);
                final String ssString = ss;
                s = g.toJson(fieldInfo);


                final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/fields/add", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("qwe", response);
                        progressDialog.dismiss();
                        Gson gson = new Gson();
                        AllPlayers sucessResponse = gson.fromJson(response, AllPlayers.class);
                        int _status = sucessResponse.getStatus();

                        if(_status == 200){
                            Toast.makeText(getActivity(), "Sucess " +_status, Toast.LENGTH_LONG).show();
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
                        headers.put("locale", "en");
                        headers.put("Content-Type", "application/x-www-form-urlencoded");
                        return headers;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("fieldInfo", ssString);
                        params.put("pictures", imgStr);
                        params.put("operating", jasonn);
                        params.put("stand_capacity", Globels.CAPASITY);
                        return params;
                    }
                };;
                requestQueue.add(stringRequest);
                break;
        }
    }
}