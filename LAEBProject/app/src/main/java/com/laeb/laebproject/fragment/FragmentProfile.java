package com.laeb.laebproject.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.laeb.laebproject.MenuActivity;
import com.laeb.laebproject.ProfileActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.adapters.FootBallFieldsAdapter;
import com.laeb.laebproject.model.City;
import com.laeb.laebproject.model.UpComingGames;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tariq on 8/17/2017.
 */

public class FragmentProfile extends Fragment {
    EditText Edt_Full_Name;
    EditText Edt_DOB;
    EditText ed_image;
    EditText ed_gender;
    EditText Edt_Nick;
    EditText Edt_Height;
    EditText Edt_Weight;
    EditText PlayRole;
    EditText fc_local;
    EditText Place_of_Birth;
    EditText fc_International;
    Spinner EDT_City;

    DatePickerDialog datePickerDialog;
    JSONArray jsonarray;
    ArrayList<City> cities;
    ArrayList<String> worldlist;
    TextView SaveProfile;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.full_profile, container, false);
        TextView saveprofile = (TextView) v.findViewById(R.id.tv_save);

        Edt_Full_Name = (EditText) v.findViewById(R.id.ed__name);
        Edt_DOB = (EditText) v.findViewById(R.id.ed_dob);
        Edt_Nick = (EditText) v.findViewById(R.id.ed_nick_name);
        EDT_City = (Spinner) v.findViewById(R.id.ed_city);
        Edt_Height = (EditText) v.findViewById(R.id.ed__height);
        Edt_Weight = (EditText) v.findViewById(R.id.ed_weight);
        Place_of_Birth = (EditText) v.findViewById(R.id.ed__district);
        PlayRole = (EditText) v.findViewById(R.id.ed_you_player);
        fc_local = (EditText) v.findViewById(R.id.ed_local_fvt_club);
        fc_International = (EditText) v.findViewById(R.id.ed_intl_fvt_club);

        Edt_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(getActivity(),R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                Edt_DOB.setText(year + "-"
                                        + (monthOfYear + 1) + "-" + dayOfMonth);

                            }

                        }, mYear, mMonth, mDay);

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        datePickerDialog.show();
                    }
                }, 100);

            }
        });
        new AsyncTask<String, String, ArrayList<String>>() {

            @Override
            protected ArrayList<String> doInBackground(String... params) {
                try {

                    String response = makePostRequest("http://192.169.138.14:4000/api/teams/getCities",
                            null,
                            getActivity(), "GET");

                    JSONObject jsonobject = new JSONObject(response);
                    cities = new ArrayList<City>();
                    jsonarray = jsonobject.getJSONArray("cities");
                    worldlist = new ArrayList<String>();
                    for (int i = 0; i < jsonarray.length(); i++) {
                        jsonobject = jsonarray.getJSONObject(i);
                        City city = new City();
                        city.setName(jsonobject.optString("city_name"));
                        city.setId(jsonobject.optInt("city_id"));
                        cities.add(city);

                        worldlist.add(jsonobject.optString("city_name"));
                    }
                    return worldlist;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return null;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }

            }


            @Override
            protected void onPostExecute(ArrayList<String> s) {
                super.onPostExecute(s);
                Spinner mySpinner = (Spinner) v.findViewById(R.id.selectCity);


                // Spinner adapter
                mySpinner
                        .setAdapter(new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_dropdown_item,
                                worldlist));

                // Spinner on item click listener
                mySpinner
                        .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> arg0,
                                                       View arg1, int position, long arg3) {


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                                // TODO Auto-generated method stub
                            }
                        });
            }


        }.execute("");
        saveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String mName = Edt_Full_Name.getText().toString();
                String mDOB = Edt_DOB.getText().toString();
                String mNick = Edt_Nick.getText().toString();
                String mHeight = Edt_Height.getText().toString();
                String mWeight = Edt_Weight.getText().toString();
                String mPlayRole = PlayRole.getText().toString();
                String mLocal = fc_local.getText().toString();
                String mInter = fc_International.getText().toString();
                String mDistrict = Place_of_Birth.getText().toString();
                String mCity = "2";

                HashMap<String, String> param = new HashMap<String, String>();
                param.put("name", mName);
                param.put("image", "base64image");
                param.put("nickname", mNick);
                param.put("email", "imran@yahoo.com" );
                param.put("city", mCity);
                param.put("dob", mDOB);
                param.put("gender", "M");
                param.put("place_of_birth", mDistrict);
                param.put("height", mHeight);
                param.put("weight", mWeight);
                param.put("playing_role", mPlayRole);
                param.put("fc_local", mLocal);
                param.put("fc_international", mInter);

                final RequestParams paramss = new RequestParams(param);

                new AsyncTask<String, String, String>() {

                    @Override
                    protected String doInBackground(String... params) {
                        try {

                            String response = makePostRequest("http://192.169.138.14:4000/api/profile/update ",
                                    paramss.toString(),
                                    getActivity(), "POST");
                            Log.i("asd", "---------------- this is response : " + response);
                            return "Success";
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            return "";
                        }
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        //startActivity(new Intent(getActivity(), MenuActivity.class));
                    }


                }.execute("");
            }
        });
        return v;
    }

    public String makePostRequest(String stringUrl, String payload,
                                  Context context, String Method) throws IOException {

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
        uc.setRequestProperty("x-access-key", "ADBB-6CA3-15AE-359E");
        uc.setRequestMethod(Method);
        uc.setDoInput(true);
        uc.setInstanceFollowRedirects(false);
        uc.connect();
        if (payload != null) {
            OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");
            writer.write(payload);
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
            ex.printStackTrace();
        }

        uc.disconnect();
        return jsonString.toString();
    }
}