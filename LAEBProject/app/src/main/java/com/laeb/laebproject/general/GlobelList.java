package com.laeb.laebproject.general;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.laeb.laebproject.model_create_team.list_city_and_fields.AllCitiesFields;
import com.laeb.laebproject.model_create_team.list_city_and_fields.City;
import com.laeb.laebproject.model_create_team.list_city_and_fields.MyCityField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tariq on 8/30/2017.
 */

public class GlobelList {
    //public static List<>

    public static List<City> cities;
    public static List<MyCityField> fields;

    public static void getCities(Context context){

        final ProgressDialog progressDialog =  new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final Context con = context;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.169.138.14:4000/api/teams/getCitiesAndFields", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                AllCitiesFields sucessResponse = gson.fromJson(response, AllCitiesFields.class);
                int _status = sucessResponse.getStatus();
                cities = sucessResponse.getCities();
                fields = sucessResponse.getMyCityFields();
                Log.v("edc", "==== "+cities.size());

                Toast.makeText(con, "sucessful "+cities.get(2).getCityName(), Toast.LENGTH_LONG).show();
                if(_status == 200){

                }else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.v("wsx", "========   "+error+"");
                Toast.makeText(con, "Unable to connect...", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-access-key", Globels.ACCESS_KEY);
                headers.put("x-access-token", Prefs.getString(con, Prefs.auth_key));
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
