package com.laeb.laebproject.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.laeb.laebproject.MultiSelectionSpinner;
import com.laeb.laebproject.ProfileActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model.City;
import com.laeb.laebproject.model.Days;
import com.laeb.laebproject.model.PlayerPosition;
import com.laeb.laebproject.model_create_team.AllPlayers;
import com.laeb.laebproject.model_create_team.Datum;
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
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tariq on 8/17/2017.
 */

public class FragmentProfile extends Fragment {
    EditText Edt_Full_Name;
    EditText Edt_DOB;
    EditText Edt_Nick;
    EditText Edt_Height;
    EditText Edt_Weight;
    Spinner mySpinner;
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
    String mName;
    String mDOB;
    String mNick;
    String mHeight;
    String mWeight;
    String mLocal;
    String mInter;
    String mDistrict;
    CircleImageView circleView;
    SpinnerAdapter adap;
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
        circleView = (CircleImageView) v.findViewById(R.id.imageView81);
        fc_local = (EditText) v.findViewById(R.id.ed_local_fvt_club);
        fc_International = (EditText) v.findViewById(R.id.ed_intl_fvt_club);
        //circleView.setImageBitmap(yourSelectedImage);
        GetProfile();

        List<String> listOfPlayerRoles= new ArrayList<String>();
        listOfPlayerRoles.add("Defender");
        listOfPlayerRoles.add("Goal Keeper");
        listOfPlayerRoles.add("Midfielder");
        listOfPlayerRoles.add("Striker");
        // Edt_Full_Name.setText(Prefs.getString(getActivity(), "name"));

       /* SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Edt_DOB.setText(input.format(input.parse(Prefs.getString(getActivity(), "dob"))));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        spn_position = (Spinner) v.findViewById(R.id.ed_select_position);
        spn_position.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listOfPlayerRoles));

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
                Refree = "0";
            }
        });
        ed_refree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgResource = R.drawable.tickselected;
                ed_refree.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                imgResource = R.drawable.tick;
                ed_player.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                Refree = "1";
                Player = "0";
            }
        });


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
                mySpinner = (Spinner) v.findViewById(R.id.selectCity);


                // Spinner adapter
//                mySpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
//                                android.R.layout.simple_spinner_dropdown_item,
//                                worldlist));
                 adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, worldlist);
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

                mName = Edt_Full_Name.getText().toString();
                mDOB = Edt_DOB.getText().toString();
                mNick = Edt_Nick.getText().toString();
                mHeight = Edt_Height.getText().toString();
                mWeight = Edt_Weight.getText().toString();
                mLocal = fc_local.getText().toString();
                mInter = fc_International.getText().toString();
                mDistrict = Place_of_Birth.getText().toString();

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

               /* new AsyncTask<String, String, String>() {

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


                }.execute("");*/
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/profile/v2/settings", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("qwe", response);
                        progressDialog.dismiss();
                        Gson gson = new Gson();
                        AllPlayers sucessResponse = gson.fromJson(response, AllPlayers.class);
                        int _status = sucessResponse.getStatus();

                        if (_status == 200) {
                            Toast.makeText(getActivity(), "Sucess " + _status, Toast.LENGTH_LONG).show();
                        } else {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.v("wsx", "========   " + error + "");
                        Toast.makeText(getActivity(), "Unable to connect...", Toast.LENGTH_LONG).show();
                    }
                }) {
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
                        Map<String, String> param = new HashMap<>();
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
                        return param;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });
        return v;
    }

    private void GetProfile() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.169.138.14:4000/api/profile/getProfile", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("asd", response);
                JSONObject jSon = null;
                progressDialog.dismiss();
                try {
                    jSon = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                Datum sucessResponse = null;
                try {
                    sucessResponse = gson.fromJson(jSon.get("data").toString(), Datum.class);
                    if (sucessResponse.getPicture().length() > 10) {
                        byte[] decodedString = Base64.decode(sucessResponse.getPicture(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        circleView.setImageBitmap(decodedByte);
                    }

                    Edt_Height.setText(sucessResponse.getHeight().toString());
                    Edt_Weight.setText(sucessResponse.getWeight().toString());
                    Edt_Nick.setText(sucessResponse.getNick());
                    Place_of_Birth.setText(sucessResponse.getDistrict());
                    fc_local.setText(sucessResponse.getFcLocal());
                    fc_International.setText(sucessResponse.getFcInternational());
                    EDT_City.setSelection(getIndex(mySpinner,sucessResponse.getCity()));
                    Edt_Full_Name.setText(sucessResponse.getName());
                    if (sucessResponse.getPlayer() == 1) {
                        int imgResource = R.drawable.tickselected;
                        ed_player.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                    }else
                    {
                        int imgResource = R.drawable.tickselected;
                        ed_refree.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                    }
                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Edt_DOB.setText(input.format(input.parse(sucessResponse.getDob())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.v("wsx", "========   " + error + "");
                Toast.makeText(getActivity(), "Unable to connect...", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-access-key", Globels.ACCESS_KEY);
                headers.put("x-access-token", Prefs.getString(getActivity(), Prefs.auth_key));
                headers.put("locale", "en");
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }

        };
        requestQueue.add(stringRequest);
    }
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
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