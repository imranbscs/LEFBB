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
    String json;

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

//                Gson gson = new Gson();
//                String json = gson.toJson(TestStaticMethod.getAll());
//                Log.v("ppp", "====== " + json);
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

//                fieldInfo.nearby = "E11";
//                String s = gson.toJson(fieldInfo);
//

                Gson g = new Gson();
                json = g.toJson(TestStaticMethod.getAll());
                fieldInfo.nearby = "E11";

                String ss = g.toJson(fieldInfo);
                final String ssString = ss;
                String s = g.toJson(fieldInfo);


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
                        headers.put("locale", Globels.LOCAL);
                        headers.put("Content-Type", Globels.CONTENT_TYPE);
                        return headers;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("fieldInfo", ssString);
                        params.put("pictures", Globels.CREATE_FIELD_IMAGE);
                        params.put("operating", json);
                        params.put("stand_capacity", Globels.CAPASITY);
                        return params;
                    }
                };;
                requestQueue.add(stringRequest);
                break;







   ////////////////////////////////////////////////////////////////////////////////////////////////////////

//                final RequestParams paramss = new RequestParams();
//                paramss.put("fieldInfo", ss);
//                paramss.put("pictures", " [{\"image\":\"base64string1\"},{\"image\":\"base64string3\"}]");
//                paramss.put("operating", json);
//                paramss.put("stand_capacity", 5000);
//                Log.i("asd", "---------------- this is response : " + paramss.toString());
//                new AsyncTask<String, String, String>() {
//
//                    @Override
//                    protected String doInBackground(String... params) {
//                        try {
//
//                            String response = makePostRequest("http://192.169.138.14:4000/api/fields/add",
//                                    paramss.toString(),
//                                    getActivity(), "POST");
//                            Log.i("asd", "---------------- this is response : " + response);
//                            return "Success";
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                            return "";
//                        }
//                    }
//
//                    @Override
//                    protected void onPostExecute(String s) {
//                        super.onPostExecute(s);
////                        FragmentFootballFields fragment = new FragmentFootballFields();
////                        ((CreateFieldActivity) getActivity()).addFragment(fragment);
//                        getActivity().finish();
//                        Log.i("asd", "---------------- this is response : " + s);
//                    }
//                }.execute("");
//                Log.i("asd", s);
//
//                break;

     ///////////////////////////////////////////////////////////////////////////////////////////////////////











        }

    }

    public String makePostRequest(String stringUrl, String payload, Context context, String Method) throws IOException {

        URL url = new URL(stringUrl);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();

        String line;
        StringBuffer jsonString = new StringBuffer();
        SharedPreferences channel = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String strChannel = channel.getString("token", "Default").toString();
        Log.i("asd", "---------------- this is response : " + strChannel);
        uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        uc.setRequestProperty("x-access-token", strChannel);
        uc.setRequestProperty("locale", "en");
        String android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        uc.setRequestProperty("x-access-key", "ADBB-6CA3-15AE-359E");
        // uc.setRequestProperty("device", android_id);
        uc.setRequestMethod(Method);
        uc.setDoInput(true);
        uc.setInstanceFollowRedirects(false);
        uc.connect();
        if (payload != null) {
            OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");
            Log.i("asd", "Connected");
            writer.write(payload);
            Log.i("asd", "Connected");
            writer.close();
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
                Log.i("asd", line);
            }
            br.close();
        } catch (Exception ex) {
            Log.i("asd", "error");
            ex.printStackTrace();
        }

        uc.disconnect();
        return jsonString.toString();
    }

    public void addAlotAndField(){
//        final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/fields/add", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.v("qwe", response);
//                progressDialog.dismiss();
//                Gson gson = new Gson();
//                AllPlayers sucessResponse = gson.fromJson(response, AllPlayers.class);
//                int _status = sucessResponse.getStatus();
//
//                if(_status == 200){
//                    Toast.makeText(getActivity(), "Sucess " +_status, Toast.LENGTH_LONG).show();
//                }else {
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Log.v("wsx", "========   "+error+"");
//                Toast.makeText(getActivity(), "Unable to connect...", Toast.LENGTH_LONG).show();
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<>();
//                headers.put("x-access-key", Globels.ACCESS_KEY);
//                headers.put("x-access-token", Prefs.getString(getActivity(), Prefs.auth_key));
//                headers.put("locale", Globels.LOCAL);
//                headers.put("Content-Type", Globels.CONTENT_TYPE);
//                return headers;
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("fieldInfo", s);
//                params.put("pictures", "");
//                params.put("operating", "");
//                params.put("stand_capacity", "");
//                return params;
//            }
//        };;
//
//        requestQueue.add(stringRequest);
    }

}