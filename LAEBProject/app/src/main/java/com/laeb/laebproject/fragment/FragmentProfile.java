package com.laeb.laebproject.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.laeb.laebproject.general.GlobelList;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model.City;
import com.laeb.laebproject.model.Days;
import com.laeb.laebproject.model.PlayerPosition;
import com.laeb.laebproject.model_create_team.AllPlayers;
import com.laeb.laebproject.model_create_team.Datum;
import com.loopj.android.http.RequestParams;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
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
    Bitmap bitmap;
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
    List<com.laeb.laebproject.model_create_team.list_city_and_fields.City> citis;
    List<String> cityStr;
    TextView SaveProfile;
    String mName;
    String mDOB;
    String mNick;
    String mHeight;
    String mWeight;
    String mLocal;
    String mInter;
    String mDistrict;
    CircularImageView circleView;
    ImageView addpic;
    ArrayAdapter adap;
    String mImage;
    ArrayAdapter oad;

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
        spn_position = (Spinner) v.findViewById(R.id.ed_select_position);
        mySpinner = (Spinner) v.findViewById(R.id.selectCity);
        ed_refree = (TextView) v.findViewById(R.id.ed_refree);
        circleView = (CircularImageView) v.findViewById(R.id.imageView82);
        fc_local = (EditText) v.findViewById(R.id.ed_local_fvt_club);
        fc_International = (EditText) v.findViewById(R.id.ed_intl_fvt_club);
        //circleView.setImageBitmap(yourSelectedImage);
        addpic = (ImageView) v.findViewById(R.id.addPic);
        addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });


        List<String> listOfPlayerRoles = new ArrayList<String>();
        listOfPlayerRoles.add("Defender");
        listOfPlayerRoles.add("Goal Keeper");
        listOfPlayerRoles.add("Midfielder");
        listOfPlayerRoles.add("Striker");


        // spn_position = (Spinner) v.findViewById(R.id.ed_select_position);
        oad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listOfPlayerRoles);
        oad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listOfPlayerRoles);
        spn_position.setAdapter(oad);


        final MultiSelectionSpinner spn_days = (MultiSelectionSpinner) v.findViewById(R.id.ed_schedule);

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

               /*HashMap<String, String> param = new HashMap<String, String>();
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
                param.put("fc_international", mInter);*/

                //  final RequestParams paramss = new RequestParams(param);

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
                        // Datum sucessResponse = gson.fromJson(response, Datum.class);
                        // int _status = sucessResponse.getStatus();


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
                        Log.i("asd", mHeight);
                        Map<String, String> param = new HashMap<>();
                        param.put("name", mName);
                        param.put("image", "im");
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

                if (Refree == "1") {
                    requestQueue = Volley.newRequestQueue(getActivity());
                    stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/profile/v2/refereeSchedule", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.v("qwe", response);
                            progressDialog.dismiss();
                            Gson gson = new Gson();
                            // Datum sucessResponse = gson.fromJson(response, Datum.class);
                            // int _status = sucessResponse.getStatus();


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
                            Log.i("asd", spn_days.getSelectedItemsAsString() + "");
                            String operating = "{";

                                if (spn_days.getSelectedItemsAsString().contains("Sunday"))
                                    operating += " sun=1,";
                                else
                                    operating += "sun=0,";
                            if (spn_days.getSelectedItemsAsString().contains("Monday"))
                                operating += " mon=1,";
                            else
                                operating += " mon=0,";
                            if (spn_days.getSelectedItemsAsString().contains("Tuesday"))
                                operating += " tue=1,";
                            else
                                operating += "tue=0,";
                            if (spn_days.getSelectedItemsAsString().contains("Wednesday"))
                                operating += " wed=1,";
                            else
                                operating += " wed=0,";
                            if (spn_days.getSelectedItemsAsString().contains("Thursday"))
                                operating += " thu=1,";
                            else
                                operating += "thu=0,";
                            if (spn_days.getSelectedItemsAsString().contains("Friday"))
                                operating += " fri=1,";
                            else
                                operating += "fri=0,";
                            if (spn_days.getSelectedItemsAsString().contains("Saturday"))
                                operating += " sat=1,";
                            else
                                operating += "sat=0,";

operating+= "}";
                            operating = operating.replace(",}","}");
Log.i("asd",operating);
                            try {
                                JSONObject j = new JSONObject(operating);
                                param.put("operating", j.toString() );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            return param;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetProfile();
        getCityStr();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1234:
                if (resultCode == getActivity().RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    InputStream imageStream;
                    Bitmap yourSelectedImage;
                    try {
                        imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                        yourSelectedImage = BitmapFactory.decodeStream(imageStream);

                        CircularImageView imageView = (CircularImageView) getView().findViewById(R.id.imageView82);
                        //CircleImageView imageView = (CircleImageView) findViewById(R.id.imageView81);
                        imageView.setImageResource(R.drawable.image_square);
                        //imageView.setImageBitmap(yourSelectedImage);

                        yourSelectedImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        mImage = imageToString(yourSelectedImage);
                        // mImage = myBase64Image;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                }
        }
    }

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    private void GetProfile() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.169.138.14:4000/api/profile/getProfile", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("asd", "----abc " + response);
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
                    // Picasso.with(getActivity()).load(sucessResponse.getPicture()).into(circleView);
                    fc_local.setText(sucessResponse.getFcLocal());
                    fc_International.setText(sucessResponse.getFcInternational());
                    spn_position.setSelection(oad.getPosition(sucessResponse.getPlayingRole()));
                    Player = sucessResponse.getPlayer() + "";
                    Refree = sucessResponse.getRefree() + "";
                    mySpinner.setSelection(adap.getPosition(sucessResponse.getCity()));

                    //   EDT_City.setSelection(getIndex(mySpinner, sucessResponse.getCity()));
                    Edt_Full_Name.setText(sucessResponse.getName());
                    if (sucessResponse.getPlayer() == 1) {
                        int imgResource = R.drawable.tickselected;
                        ed_player.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                    } else {
                        int imgResource = R.drawable.tickselected;
                        ed_refree.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                    }
                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Edt_DOB.setText(input.format(input.parse(sucessResponse.getDob())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // InputStream imageStream;
                    // Bitmap yourSelectedImage;

                    // imageStream = getActivity(). getContentResolver().openInputStream(Uri.parse(sucessResponse.getPicture()));
                    //  yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    //  circleView.setImageBitmap(yourSelectedImage);
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

    private int getIndex(Spinner spinner, String myString) {

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(myString)) {
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

    void getCityStr() {
        citis = GlobelList.cities;
        cityStr = new ArrayList<>();
        final List<com.laeb.laebproject.model_create_team.list_city_and_fields.City> cc = GlobelList.cities;
        Toast.makeText(getActivity(), GlobelList.cities.size() + "  ", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < GlobelList.cities.size(); i++) {
            cityStr.add(GlobelList.cities.get(i).getCityName());
        }
        //spn_city = (Spinner) getView().findViewById(R.id.ed_city);
        adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, cityStr);

        mySpinner.setAdapter(adap);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                com.laeb.laebproject.model_create_team.list_city_and_fields.City areaName = (com.laeb.laebproject.model_create_team.list_city_and_fields.City) cc.get(position);
                mCity_Id = areaName.getCityId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
}