package com.laeb.laebproject;

import android.app.DatePickerDialog;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.laeb.laebproject.model.City;
import com.laeb.laebproject.model.Custom;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class ProfileActivity extends AppCompatActivity {

    EditText Edt_Full_Name;
    EditText Edt_Email;
    EditText Edt_DOB;
    DatePickerDialog datePickerDialog;
    JSONArray jsonarray;
    ArrayList<City> cities;
    ArrayList<String> worldlist;
    TextView SaveProfile;
    String gender = "M";
    TextView Male;
    TextView Female;
    int mCity_Id = 0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Spinner mySpinner;
    SharedPreferences channel;

    public void FillTheForm() throws JSONException {
        String user =  channel.getString("user","default");
        JSONObject obj = new JSONObject(user);
        Edt_DOB.setText(obj.getString("dob"));
        Log.i("asd", "---------------- this is gender : " + obj.getString("gender"));
        Edt_Full_Name.setText(obj.getString("name"));
        mySpinner.setSelection(getIndex(mySpinner,cities.get(obj.getInt("city")).getName()));
        if (obj.getString("gender") == "M")
        {
            btnMale(Male);
        }else
        {
            btnFemale(Female);
        }
    }

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i-1;
                break;
            }
        }
        return index;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Edt_DOB = (EditText) findViewById(R.id.ed_dob);
        Male = (TextView) findViewById(R.id.txtMale);
        Female = (TextView) findViewById(R.id.txtFemale);
        getSupportActionBar().hide();
        channel = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // perform click event on edit text
        Edt_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(ProfileActivity.this, R.style.DialogTheme,
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

                    String response = makePostRequest("http://192.169.138.14:4000/api/teams/getCities", null, getApplicationContext(), "GET");

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
                mySpinner = (Spinner) findViewById(R.id.ed_city);

                SpinnerAdapter adap = new ArrayAdapter<String>(ProfileActivity.this, R.layout.spinner_item, worldlist);


                mySpinner.setAdapter(adap);
                // Spinner on item click listener
                mySpinner
                        .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> arg0,
                                                       View arg1, int position, long arg3) {

                                City areaName = (City) cities.get(position);
                                mCity_Id = areaName.getId();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                                // TODO Auto-generated method stub
                            }
                        });
                try {
                    FillTheForm();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }.execute("");

        Edt_Full_Name = (EditText) findViewById(R.id.ed_fumm_name);

        Edt_Email = (EditText) findViewById(R.id.ed_email);
        // Edt_DOB = (EditText) findViewById(R.id.ed_dob);

        SaveProfile = (TextView) findViewById(R.id.tv_saveProfile);


        SaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validation()) {
                    return;
                }

                String mName = Edt_Full_Name.getText().toString();

                String mEmail = Edt_Email.getText().toString();
                String mDOB = Edt_DOB.getText().toString();
                final HashMap<String, String> param = new HashMap<String, String>();
                param.put("name", mName);
                param.put("image", "base64image");
                param.put("email", mEmail);
                param.put("city", mCity_Id + "");
                param.put("dob", mDOB);
                param.put("gender", gender);

                final RequestParams paramss = new RequestParams(param);

                new AsyncTask<String, String, String>() {

                    @Override
                    protected String doInBackground(String... params) {
                        try {

                            String response = makePostRequest("http://192.169.138.14:4000/api/profile/v2/basic",
                                    paramss.toString(),
                                    getApplicationContext(), "POST");
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
                        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                        Log.i("asd", param.toString());
                        Custom custom = new Custom();
                        custom.setList(param);
                        i.putExtra("user", custom);
                        startActivity(i);
                    }


                }.execute("");
                getSupportActionBar().hide();


            }
        });


    }


    public String makePostRequest(String stringUrl, String payload, Context context, String Method) throws IOException {

        URL url = new URL(stringUrl);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();

        String line;
        StringBuffer jsonString = new StringBuffer();

        String strChannel = channel.getString("token", "Default").toString();
        Log.i("asd", "---------------- this is response : " + strChannel);
        uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        uc.setRequestProperty("x-access-token", strChannel);
        uc.setRequestProperty("locale", "en");
        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        uc.setRequestProperty("x-access-key", "ADBB-6CA3-15AE-359E");
        // uc.setRequestProperty("device", android_id);
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


    public void btnFemale(View view) {
        RelativeLayout or = (RelativeLayout) findViewById(R.id.male_selector);
        or.setVisibility(View.INVISIBLE);
        or = (RelativeLayout) findViewById(R.id.female_delector);
        or.setVisibility(View.VISIBLE);
        gender = "M";
    }

    public void btnMale(View view) {
        RelativeLayout or = (RelativeLayout) findViewById(R.id.male_selector);
        or.setVisibility(View.VISIBLE);
        or = (RelativeLayout) findViewById(R.id.female_delector);
        or.setVisibility(View.INVISIBLE);

        gender = "F";
    }

    public boolean validation() {
        boolean b = true;
        if (Edt_Full_Name.getText().toString().trim().equals("")) {
            Edt_Full_Name.setError("Invalid number or Contry code.");
            return false;
        } else {
            Edt_Full_Name.setError(null);
        }

        if (Edt_Email.getText().toString().trim().matches(emailPattern) && Edt_Email.getText().toString().length() > 0) {
            Edt_Email.setError(null);
        } else {
            Edt_Email.setError("Invalid Email.");
            return false;
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
            Toast.makeText(this, "Please select a valid date.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

}
