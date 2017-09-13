package com.laeb.laebproject.team_fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.InvitePlayerActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.YourTeamActivity;
import com.laeb.laebproject.adapter_team.AdapterInvitePlayer;
import com.laeb.laebproject.create_field_fragments.MapFragmentLeab;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model_create_team.AllPlayers;
import com.laeb.laebproject.model_create_team.Datum;
import com.laeb.laebproject.model_create_team.list_city_and_fields.City;
import com.laeb.laebproject.model_create_team.list_city_and_fields.MyCityField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tariq on 8/27/2017.
 */

public class FragmentYourTeam extends Fragment {

    ImageView homeShirt, awayShirt, logo;
    EditText colorName, coachName, teamCity;
    Spinner citySpinner, groundName;
    public final int IMG_REQUEST = 1;
    public Bitmap bitmap;
    public Bitmap bitmaplogo;
    public Bitmap bitmapAwayShirt;
    public Bitmap bitmapHomeShirt;
    public int des;
    TextView teamName;
    List<String> cityStr;
    List<String> fieldStr;
    int mCity_Id = 0;
    int mField_Id = 0;
    List<City> cities;
    List<MyCityField> fields;
    RelativeLayout color_team;
    public String logoBitmap = "fgd", homeImgBitmap = "dfg", awayShirtBitmap = "dfgd";
    private int currentColor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_your_team, container, false);
        logo = (ImageView) v.findViewById(R.id.logo);
        homeShirt  =  (ImageView) v.findViewById(R.id.addHome);
        awayShirt  =  (ImageView) v.findViewById(R.id.addAway);
        teamName  =  (TextView) v.findViewById(R.id.teme_name);
        groundName  =  (Spinner) v.findViewById(R.id.spinner_field);
        coachName  =  (EditText) v.findViewById(R.id.coah_name);
        citySpinner  =  (Spinner) v.findViewById(R.id.spinner_city);
        color_team =  (RelativeLayout) v.findViewById(R.id.color_team);

        teamName.setText(Prefs.getString(getActivity(), Prefs.TEAM_NAME));
        coachName.setText(Prefs.getString(getActivity(), Prefs.COACH));
        decodeClicked(logo, Prefs.getString(getActivity(), Prefs.TEAM_LOGO));
        decodeClicked(homeShirt, Prefs.getString(getActivity(), Prefs.HOME_SHIRT));
        decodeClicked(awayShirt, Prefs.getString(getActivity(), Prefs.AWAY_SHIRT));
        int colorInt = Integer.parseInt(Prefs.getString(getActivity(), Prefs.COLOR));
        //String hexColor = String.format("#%06X", (0xFFFFFF & colorInt));
        color_team.setBackgroundColor(colorInt);
        //logo.set

        teamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentYourPlayer fragment = new FragmentYourPlayer();
                ((YourTeamActivity) getActivity()).addFragment(fragment);
            }
        });

        return v;
    }

    public void decodeClicked(ImageView img, String bcode) {
        byte[] decodedString = Base64.decode(bcode, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        img.setImageBitmap(decodedByte);
    }


}