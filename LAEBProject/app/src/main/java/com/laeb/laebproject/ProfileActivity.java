package com.laeb.laebproject;

import android.app.DatePickerDialog;
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
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.laeb.laebproject.general.GlobelList;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model_create_team.AllPlayers;
import com.laeb.laebproject.model_create_team.list_city_and_fields.City;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    EditText Edt_Full_Name;
    EditText Edt_Email;
    EditText Edt_DOB;
    DatePickerDialog datePickerDialog;
    List<City> citis;
    TextView SaveProfile;
    String gender = "M";
    TextView Male;
    TextView Female;
    int mCity_Id = 0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Spinner mySpinner;
    SharedPreferences channel;
    String mImage = "image";
    List<String> cityStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Edt_DOB = (EditText) findViewById(R.id.ed_dob);
        Male = (TextView) findViewById(R.id.txtMale);
        Female = (TextView) findViewById(R.id.txtFemale);
        getSupportActionBar().hide();
        channel = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        Edt_Full_Name = (EditText) findViewById(R.id.ed_fumm_name);
        Edt_Email = (EditText) findViewById(R.id.ed_email);
        SaveProfile = (TextView) findViewById(R.id.tv_saveProfile);

        ImageView btn_choose_photo = (ImageView) findViewById(R.id.addPic); // Replace with id of your button.
        btn_choose_photo.setOnClickListener(btnChoosePhotoPressed);
        getCityStr();
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

        SaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validation()) {
                    return;
                }

                final String mName = Edt_Full_Name.getText().toString();
                final String mEmail = Edt_Email.getText().toString();
                final String mDOB = Edt_DOB.getText().toString();

                final ProgressDialog progressDialog =  new ProgressDialog(ProfileActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                RequestQueue requestQueue = Volley.newRequestQueue(ProfileActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/profile/v2/basic", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("qwe", response);
                        progressDialog.dismiss();
                        Gson gson = new Gson();
                        AllPlayers sucessResponse = gson.fromJson(response, AllPlayers.class);
                        int _status = sucessResponse.getStatus();

                        if(_status == 200){
                            Toast.makeText(ProfileActivity.this, "Sucess", Toast.LENGTH_LONG).show();
                            Prefs.putString(ProfileActivity.this, "name", mName);
                            Prefs.putString(ProfileActivity.this, "image", mImage);
                            Prefs.putString(ProfileActivity.this, "email", mEmail);
                            Prefs.putString(ProfileActivity.this, "city", mCity_Id+"");
                            Prefs.putString(ProfileActivity.this, "dob", mDOB);
                            startActivity(new Intent(ProfileActivity.this, MenuActivity.class));

                        }else {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.v("wsx", "========   "+error+"");
                        Toast.makeText(ProfileActivity.this, "Unable to connect...", Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("x-access-key", Globels.ACCESS_KEY);
                        headers.put("x-access-token", Prefs.getString(ProfileActivity.this, Prefs.auth_key));
                        headers.put("locale", Globels.LOCAL);
                        headers.put("Content-Type", Globels.CONTENT_TYPE);
                        return headers;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        Log.v("gggg",mName+"==="+mImage+"==="+mEmail+"===="+mCity_Id+"===="+mDOB+"===="+gender);
                        params.put("name", mName);
                        params.put("image", mImage);
                        params.put("email", mEmail);
                        params.put("city", mCity_Id + "");
                        params.put("dob", mDOB);
                        params.put("gender", gender);
                        return params;
                    }
                };;

                requestQueue.add(stringRequest);

            }
        });

    }

    public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1234:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    InputStream imageStream;
                    Bitmap yourSelectedImage;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                        yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                        CircleImageView imageView = (CircleImageView) findViewById(R.id.imageView81);
                        imageView.setImageBitmap(yourSelectedImage);
                        yourSelectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        mImage=  imageToString(yourSelectedImage);
                       // mImage = myBase64Image;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                }
        }

    };

    public View.OnClickListener btnChoosePhotoPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            final int ACTIVITY_SELECT_IMAGE = 1234;
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
        }
    };

    public void btnFemale(View view) {
        RelativeLayout or = (RelativeLayout) findViewById(R.id.male_selector);
        or.setVisibility(View.INVISIBLE);
        or = (RelativeLayout) findViewById(R.id.female_delector);
        or.setVisibility(View.VISIBLE);
        gender = "F";
    }

    public void btnMale(View view) {
        RelativeLayout or = (RelativeLayout) findViewById(R.id.male_selector);
        or.setVisibility(View.VISIBLE);
        or = (RelativeLayout) findViewById(R.id.female_delector);
        or.setVisibility(View.INVISIBLE);
        gender = "M";
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

    void getCityStr(){
        citis = GlobelList.cities;
        cityStr = new ArrayList<>();
        final List<City> cc = GlobelList.cities;
        Toast.makeText(this, GlobelList.cities.size()+"  ", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < GlobelList.cities.size(); i++){
            cityStr.add(GlobelList.cities.get(i).getCityName());
        }
        mySpinner = (Spinner) findViewById(R.id.ed_city);
        SpinnerAdapter adap = new ArrayAdapter<String>(this, R.layout.spinner_item, cityStr);
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
