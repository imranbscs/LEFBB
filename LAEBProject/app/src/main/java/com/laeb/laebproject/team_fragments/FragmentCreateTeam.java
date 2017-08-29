package com.laeb.laebproject.team_fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.laeb.laebproject.BookingActivity;
import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.InvitePlayerActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.YourTeamActivity;
import com.laeb.laebproject.create_field_fragments.MapFragment;
import com.laeb.laebproject.fragments_booking.FragmentSearchFacilities;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model_create_team.AllCities;
import com.laeb.laebproject.model_create_team.City;
import com.laeb.laebproject.model_create_team.SucessResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tariq on 8/26/2017.
 */

public class FragmentCreateTeam extends Fragment implements View.OnClickListener{

    LinearLayout createBtn;
    ImageView homeShirt, awayShirt, addHome, addAway, teamLogo;
    EditText teamName, colorName, coachName, groundName, teamCity;
    Spinner citySpinner;
    public final int IMG_REQUEST = 1;
    public Bitmap bitmap;
    public Bitmap bitmaplogo;
    public Bitmap bitmapAwayShirt;
    public Bitmap bitmapHomeShirt;
    public int des;
    List<String> cityStr;
    int mCity_Id = 0;
    List<City> cities;
    public String logoBitmap = "fgd", homeImgBitmap = "dfg", awayShirtBitmap = "dfgd";
    //public List<City> cities;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v("wsa", Prefs.getString(getActivity(), Prefs.auth_key));

        View v = inflater.inflate(R.layout.fragment_createteam, container, false);
        LinearLayout createBtn = (LinearLayout) v.findViewById(R.id.creatBtn);
        homeShirt  =  (ImageView) v.findViewById(R.id.home_shirt);
        awayShirt  =  (ImageView) v.findViewById(R.id.away_shirt);
        addHome  =  (ImageView) v.findViewById(R.id.add_home_shirt);
        addAway  =  (ImageView) v.findViewById(R.id.add_away_shirt);
        teamLogo  =  (ImageView) v.findViewById(R.id.team_logo);
        teamName  =  (EditText) v.findViewById(R.id.team_name);
        colorName  =  (EditText) v.findViewById(R.id.team_color);
        coachName  =  (EditText) v.findViewById(R.id.coach_name);
        groundName  =  (EditText) v.findViewById(R.id.fvt_ground);
        //teamCity  =  (EditText) v.findViewById(R.id.team_);

        cities = new ArrayList<>();
        cityStr = new ArrayList<>();
        getData();

        teamLogo.setOnClickListener(this);
        homeShirt.setOnClickListener(this);
        awayShirt.setOnClickListener(this);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTeam();
            }
        });
        return v;
    }

    public void addDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.color_dialog);
        dialog.show();
        TextView okBtn = (TextView) dialog.findViewById(R.id.done);

//        final String finalRateStr = rateStr;
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(getActivity(), YourTeamActivity.class));
            }
        });
    }

    public void createTeam(){

        final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/teams/create", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                SucessResponse sucessResponse = gson.fromJson(response, SucessResponse.class);
                String _message = sucessResponse.messege;
                int _status = sucessResponse.status;
                Toast.makeText(getActivity(), "sucessful", Toast.LENGTH_LONG).show();
                if(_status == 200){
                    startActivity(new Intent(getActivity(), InvitePlayerActivity.class));
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
                //EditText teamName, colorName, coachName, groundName, teamCity;
//                params.put("team_name", teamName.getText().toString());
//                params.put("color", colorName.getText().toString());
//                params.put("coach", coachName.getText().toString());
                params.put("team_name", "dsfs");
                params.put("color", "sdvs");
                params.put("coach", "dsfs");
                params.put("city", "1");
                params.put("shirt_home", homeImgBitmap);
                params.put("shirt_away", awayShirtBitmap);
                params.put("logo", logoBitmap);
                params.put("found_date", "2017-05-15");
                params.put("country", "1");
                params.put("avg_age", "14");
                params.put("city_flexible", "0");
                params.put("field_flexible", "0");
                params.put("field_id", "5");
                return params;
            }
        };;

        requestQueue.add(stringRequest);
    }

    public void getImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                if(des == 1) {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                    //imageView.setImageBitmap(bitmap);
                    logoBitmap = imageToString(bitmap);
                }else if(des == 2){
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                    //imageView.setImageBitmap(bitmap);
                    homeImgBitmap = imageToString(bitmap);
                }else{
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                    //imageView.setImageBitmap(bitmap);
                    awayShirtBitmap = imageToString(bitmap);
                }
                //Log.v("ttt", pp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.team_logo:
                des = 1;
                getImageFromGallery();
                break;
            case R.id.home_shirt:
                des = 2;
                getImageFromGallery();
                break;
            case R.id.away_shirt:
                des = 3;
                getImageFromGallery();
                break;
        }
    }

    public void getData(){

        final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.169.138.14:4000/api/teams/getCities", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                AllCities sucessResponse = gson.fromJson(response, AllCities.class);
                int _status = sucessResponse.getStatus();
                cities = sucessResponse.getCities();
                Log.v("edc", "==== "+cities.size());
                getCityStr();

//                for (int i = 0; i < cities.size(); i++) {
//                    City city = new City();
//                    cityStr.
//                    worldlist.add(jsonobject.optString("city_name"));
//                }

                Toast.makeText(getActivity(), "sucessful "+cities.get(2).getCityName(), Toast.LENGTH_LONG).show();
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

    void getCityStr(){

        final List<City> cc = cities;
        Toast.makeText(getActivity(), cities.size()+"  ", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < cities.size(); i++){
            cityStr.add(cities.get(i).getCityName());
        }
        citySpinner = (Spinner) getView().findViewById(R.id.city_name);
        SpinnerAdapter adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, cityStr);
        citySpinner.setAdapter(adap);
        // Spinner on item click listener
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        City areaName = (City) cc.get(position);
                        mCity_Id = areaName.getCityId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }
                });
    }
}