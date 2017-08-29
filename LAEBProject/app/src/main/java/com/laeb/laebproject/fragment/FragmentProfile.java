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
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.laeb.laebproject.MenuActivity;
import com.laeb.laebproject.MultiSelectionSpinner;
import com.laeb.laebproject.ProfileActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.adapters.FootBallFieldsAdapter;
import com.laeb.laebproject.model.City;
import com.laeb.laebproject.model.Custom;
import com.laeb.laebproject.model.Days;
import com.laeb.laebproject.model.PlayerPosition;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
    TextView ed_player;
    TextView ed_refree;
    EditText fc_local;
    EditText Place_of_Birth;
    EditText fc_International;
    Spinner EDT_City;
    String Player = "0";
    String Refree = "0";
    String PlayingRole;
    int mCity_Id = 0;
    Spinner spn_position;
    DatePickerDialog datePickerDialog;
    JSONArray jsonarray;
    ArrayList<City> cities;
    ArrayList<String> worldlist;
    TextView SaveProfile;

    public static String[] names() {
        return Arrays.toString(Days.values()).replaceAll("^.|.$", "").split(", ");
    }

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
        ed_player = (TextView) v.findViewById(R.id.ed_you_player);
        ed_refree = (TextView) v.findViewById(R.id.ed_refree);

        Intent i = getActivity().getIntent();
        Custom custom = (Custom) i.getSerializableExtra("user");
        HashMap<String, String> param = custom.getList();

        Edt_Full_Name.setText(param.get("name"));
        Edt_DOB.setText(param.get("dob"));

        spn_position = (Spinner) v.findViewById(R.id.ed_select_position);
        spn_position.setAdapter(new ArrayAdapter<PlayerPosition>(getActivity(), android.R.layout.simple_spinner_item, PlayerPosition.values()));

//        SpinnerAdapter adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, worldlist);
//        spn_position.setAdapter(adap);

        MultiSelectionSpinner spn_days = (MultiSelectionSpinner) v.findViewById(R.id.ed_schedule);

        spn_days.setItems(names());

        ed_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgResource = R.drawable.tickselected;
                ed_player.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                imgResource = R.drawable.tick;
                ed_refree.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                Player = "1";
            }
        });
        ed_refree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgResource = R.drawable.tickselected;
                ed_refree.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                imgResource = R.drawable.tick;
                ed_player.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                Refree = "0";
            }
        });
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
                datePickerDialog = new DatePickerDialog(getActivity(), R.style.DialogTheme,
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
//                mySpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                worldlist));
                SpinnerAdapter adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, worldlist);
                mySpinner.setAdapter(adap);
                // Spinner on item click listener
                mySpinner
                        .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                                City areaName = (City) cities.get(position);
                                mCity_Id = areaName.getId();
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

                if (!validation()) {
                    return;
                }

                String mName = Edt_Full_Name.getText().toString();
                String mDOB = Edt_DOB.getText().toString();
                String mNick = Edt_Nick.getText().toString();
                String mHeight = Edt_Height.getText().toString();
                String mWeight = Edt_Weight.getText().toString();
                String mLocal = fc_local.getText().toString();
                String mInter = fc_International.getText().toString();
                String mDistrict = Place_of_Birth.getText().toString();


                HashMap<String, String> param = new HashMap<String, String>();
                param.put("name", mName);
                param.put("image", "base64image");
                param.put("nickname", mNick);
                param.put("city", mCity_Id + "");
                param.put("dob", mDOB);
                param.put("gender", "M");
                param.put("district", mDistrict);
                param.put("height", mHeight);
                param.put("weight", mWeight);
                param.put("playing_role", spn_position.getSelectedItem().toString());
                param.put("player", Player);
                param.put("refree", Refree);
                param.put("fc_local", mLocal);
                param.put("fc_international", mInter);

                final RequestParams paramss = new RequestParams(param);

                new AsyncTask<String, String, String>() {

                    @Override
                    protected String doInBackground(String... params) {
                        try {

                            String response = makePostRequest("http://192.169.138.14:4000/api/profile/v2/settings ",
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
                        if (s == "Success")
                            Toast.makeText(getActivity(), "Profile saved successfully", Toast.LENGTH_SHORT).show();
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

    public boolean validation() {
        boolean b = true;
        if (Edt_Full_Name.getText().toString().trim().equals("")) {
            Edt_Full_Name.setError("Invalid name.");
            return false;
        } else {
            Edt_Full_Name.setError(null);
        }

        if (fc_local.getText().toString().trim().equals("") && fc_local.getText().toString().length() < 1) {
            fc_local.setError("Invalid Club name.");
            return false;
        } else {
            fc_local.setError(null);
        }

        if (fc_International.getText().toString().trim().equals("")) {
            fc_International.setError("Invalid Club name");
            return false;
        } else {
            fc_International.setError(null);
        }

        if (Place_of_Birth.getText().toString().trim().equals("")) {
            Place_of_Birth.setError("Invalid Club name");
            return false;
        } else {
            Place_of_Birth.setError(null);
        }
        if (!Edt_Height.getText().toString().trim().equals("")) {
            float hieght = Float.parseFloat(Edt_Height.getText().toString());
            if (Edt_Height.getText().toString().length() < 1 && hieght > 10 && hieght < 1) {
                Edt_Height.setError("Invalid Height");
                return false;
            } else {
                Edt_Height.setError(null);
            }
        } else {
            Edt_Height.setError("Weight Required");
        }
        if (!Edt_Weight.getText().toString().trim().equals("")) {
            float wieght = Float.parseFloat(Edt_Weight.getText().toString());
            if (Edt_Weight.getText().toString().trim().equals("") && Edt_Weight.getText().toString().length() < 1 && wieght > 500 && wieght < 5) {
                Edt_Weight.setError("Invalid Weight");
                return false;
            } else {
                Edt_Weight.setError(null);
            }
        } else {
            Edt_Weight.setError("Weight required");
        }

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(Edt_DOB.getText().toString());
            fmt.format(date);
        } catch (ParseException pe) {
        }
        int diff1 = new Date().compareTo(date);

        if (diff1 < 0) {
            Toast.makeText(getActivity(), "Please select a valid date.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}