package com.laeb.laebproject.team_fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
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
import com.laeb.laebproject.InvitePlayerActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.YourTeamActivity;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model_create_team.SucessResponse;
import com.laeb.laebproject.model_create_team.TeamSucessWithId;
import com.laeb.laebproject.model_create_team.list_city_and_fields.AllCitiesFields;
import com.laeb.laebproject.model_create_team.list_city_and_fields.City;
import com.laeb.laebproject.model_create_team.list_city_and_fields.MyCityField;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yuku.ambilwarna.AmbilWarnaDialog;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tariq on 8/26/2017.
 */

public class FragmentCreateTeam extends Fragment implements View.OnClickListener{

    ImageView homeShirt, awayShirt, addHome, addAway, teamLogo, home, away;
    EditText teamName, colorName, coachName, teamCity;
    Spinner citySpinner, groundName;
    public final int IMG_REQUEST = 1;
    public Bitmap bitmap;
    public Bitmap bitmaplogo;
    public Bitmap bitmapAwayShirt;
    public Bitmap bitmapHomeShirt;
    public int des;
    List<String> cityStr;
    List<String> fieldStr;
    int mCity_Id = 0;
    int mField_Id = 0;
    List<City> cities;
    List<MyCityField> fields;
    public String logoBitmap = "fgd", homeImgBitmap = "dfg", awayShirtBitmap = "dfgd";
    private int currentColor;
    //public List<City> cities;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        currentColor = ContextCompat.getColor(getActivity(), R.color.colorAccent);

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
        //groundName  =  (Spinner) v.findViewById(R.id.fvt_ground);
        home  =  (ImageView) v.findViewById(R.id.home_shirt);
        away  =  (ImageView) v.findViewById(R.id.away_shirt);
        //teamCity  =  (EditText) v.findViewById(R.id.team_);
        //citySpinner = (Spinner) getView().findViewById(R.id.city_name);

        cities = new ArrayList<>();
        cityStr = new ArrayList<>();
        fieldStr = new ArrayList<>();
        //getData();
        getCitiesAndField();
        //GlobelList.getCities(getActivity());

        teamLogo.setOnClickListener(this);
        homeShirt.setOnClickListener(this);
        awayShirt.setOnClickListener(this);
        colorName.setOnClickListener(this);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTeam();
            }
        });

        return v;
    }

    public void createTeam(){
//        Prefs.putString(getActivity(), Prefs.TEAM_LOGO, logoBitmap);
//        Prefs.putString(getActivity(), Prefs.HOME_SHIRT, homeImgBitmap);
//        Prefs.putString(getActivity(), Prefs.AWAY_SHIRT, awayShirtBitmap);
//        Prefs.putString(getActivity(), Prefs.TEAM_NAME, teamName.getText().toString());
//        Prefs.putString(getActivity(), Prefs.COACH, coachName.getText().toString());
//        Prefs.putString(getActivity(), Prefs.COLOR, currentColor+"");
//        Prefs.putString(getActivity(), Prefs.CITY_ID, mCity_Id+"");
//        Prefs.putString(getActivity(), Prefs.FIELD_ID, ""+mField_Id);

        if(!(validation())){
            return;
        }

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
                TeamSucessWithId sucessResponse = gson.fromJson(response, TeamSucessWithId.class);
                String _message = sucessResponse.getMessage();
                int _status = sucessResponse.getStatus();
                int teamId = sucessResponse.getTeamId();
                //Toast.makeText(getActivity(), _status+": "+_message+" "+teamId, Toast.LENGTH_LONG).show();
                if(_status == 200){
                    Toast.makeText(getActivity(), _status+": "+_message+" ", Toast.LENGTH_LONG).show();
                    Prefs.putString(getActivity(), Prefs.TEAM_LOGO, logoBitmap);
                    Prefs.putString(getActivity(), Prefs.HOME_SHIRT, homeImgBitmap);
                    Prefs.putString(getActivity(), Prefs.AWAY_SHIRT, awayShirtBitmap);
                    Prefs.putString(getActivity(), Prefs.TEAM_NAME, teamName.getText().toString());
                    Prefs.putString(getActivity(), Prefs.COACH, coachName.getText().toString());
                    Prefs.putString(getActivity(), Prefs.COLOR, currentColor+"");
                    Prefs.putString(getActivity(), Prefs.CITY_ID, mCity_Id+"");
                    Prefs.putString(getActivity(), Prefs.FIELD_ID, ""+mField_Id);
                    Prefs.putString(getActivity(), Prefs.TEAM_ID, teamId+"");
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
                //EditText teamName, colorName, coachName, groundName, teamCity;ss

                Log.v("kkk", "city===="+mCity_Id);
                Log.v("jjj", "Field===="+mField_Id);

                params.put("team_name", teamName.getText().toString());
                params.put("color", currentColor+"");
                params.put("coach", coachName.getText().toString());
//                params.put("team_name", "dsfs");
//                params.put("color", ""+currentColor);
//                params.put("coach", "dsfs");
                params.put("city", ""+mCity_Id);
                params.put("shirt_home", homeImgBitmap);
                params.put("shirt_away", awayShirtBitmap);
                params.put("logo", logoBitmap);
                params.put("found_date", "2017-05-15");
                params.put("country", "1");
                params.put("avg_age", "");
                params.put("city_flexible", "0");
                params.put("field_flexible", "0");
                params.put("field_id", ""+mField_Id);
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
                    teamLogo.setImageBitmap(bitmap);
                    logoBitmap = imageToString(bitmap);
                }else if(des == 2){
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                    home.setImageBitmap(bitmap);
                    homeImgBitmap = imageToString(bitmap);
                }else{
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                    away.setImageBitmap(bitmap);
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
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50/100, byteArrayOutputStream);
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
            case R.id.team_color:
                openDialog(false);
                break;
        }
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

    void getFieldStr(){
        final List<MyCityField> cc = fields;
        Toast.makeText(getActivity(), cities.size()+"  ", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < fields.size(); i++){
            fieldStr.add(fields.get(i).getName());
        }
        groundName = (Spinner) getView().findViewById(R.id.fvt_ground);
        SpinnerAdapter adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, fieldStr);
        groundName.setAdapter(adap);
//        groundName.setInd
        // Spinner on item click listener
        groundName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                MyCityField areaName = (MyCityField) cc.get(position);
                mField_Id = areaName.getFieldId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    public boolean validation() {
        boolean b = true;
        if (teamName.getText().toString().trim().equals("") || teamName.getText().toString().trim().length()<1) {
            teamName.setError(getString(R.string.invalidName));
            return false;
        } else {
            teamName.setError(null);
        }

        if (coachName.getText().toString().trim().equals("") || coachName.getText().toString().trim().length()<1) {
            coachName.setError(getString(R.string.invalidDistrictName));
            return false;
        } else {
            coachName.setError(null);
        }

        if (colorName.getText().toString().trim().equals("") || colorName.getText().toString().trim().length()<1) {
            colorName.setError(getString(R.string.invalidGroundSize));
            return false;
        } else {
            colorName.setError(null);
        }

        return true;
    }

    private void openDialog(boolean supportsAlpha) {

        AmbilWarnaDialog dialog = new AmbilWarnaDialog(getActivity(), currentColor, supportsAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                currentColor = color;
                Log.v("fds", " ===== "+currentColor);
                colorName.setText(""+currentColor);
                //colorLayout.setBackgroundColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(getActivity(), "Action canceled!", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    void getCitiesAndField(){

        final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final Context con = getActivity();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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
                getCityStr();
                getFieldStr();
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
                Log.v("prfe", Prefs.getString(getActivity(), Prefs.auth_key));
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