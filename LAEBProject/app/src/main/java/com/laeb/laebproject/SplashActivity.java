package com.laeb.laebproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model_create_team.list_city_and_fields.AllCitiesFields;
import com.laeb.laebproject.model_create_team.list_city_and_fields.City;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    public static List<City> citiesV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getCitiesV2(this);
    }
    public static void getCitiesV2(final Activity context){

        final ProgressDialog progressDialog =  new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final Context con = context;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.169.138.14:4000/api/users/getCities", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                AllCitiesFields sucessResponse = gson.fromJson(response, AllCitiesFields.class);
                int _status = sucessResponse.getStatus();
                citiesV2 = sucessResponse.getCities();
                Log.v("edc", "==== "+citiesV2.size());

                Toast.makeText(con, ""+_status+"  "+citiesV2.size(), Toast.LENGTH_LONG).show();
                if(_status == 200){
                    context.startActivity(new Intent(context, LanguageActivity.class));
                }else {
                    Toast.makeText(con, "Unable to connect...", Toast.LENGTH_LONG).show();
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
