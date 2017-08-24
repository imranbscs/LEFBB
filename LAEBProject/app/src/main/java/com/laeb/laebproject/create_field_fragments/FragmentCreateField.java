package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.ProfileActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.model.City;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by tariq on 8/19/2017.
 */

public class FragmentCreateField extends Fragment implements View.OnClickListener {

    String Type;
    EditText ed_name;
    EditText ed_city;
    EditText ed_size;
    TextView grass_pitch;
    ImageView addImage;
    TextView clay_pitch;
    int mCity_Id = 0;
    JSONArray jsonarray;
    ArrayList<City> cities;
    ArrayList<String> worldlist;
    Spinner spn_city;
    Spinner spn_acc;
    private static final int RESULT_OK = -1;
    Bitmap image;

    private static int RESULT_LOAD_IMAGE = 1;


    View myView;
    private ImageView profImg;

    final FieldInfo fieldInfo = new FieldInfo();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_create_field, container, false);
        View b = (View) view.findViewById(R.id.nextBtn);

        ed_name = (EditText) view.findViewById(R.id.ed_name);
        ed_city = (EditText) view.findViewById(R.id.ed__district);
        ed_size = (EditText) view.findViewById(R.id.field_size);
        grass_pitch = (TextView) view.findViewById(R.id.grass_pitch);
        clay_pitch = (TextView) view.findViewById(R.id.clay_pitch);
        profImg = (ImageView) view.findViewById(R.id.picture);
        addImage = (ImageView) view.findViewById(R.id.addImage);
        spn_acc = (Spinner) view.findViewById(R.id.accamodations);
        grass_pitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView = (ImageView) getView().findViewById(R.id.imgGrass);
                imageView.setImageResource(R.drawable.tickselected);
                ImageView imageView2 = (ImageView) getView().findViewById(R.id.imgClay);
                imageView2.setImageResource(R.drawable.tick);
                fieldInfo.type  = "Grass";
            }
        });
        clay_pitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) getView().findViewById(R.id.imgClay);
                imageView.setImageResource(R.drawable.tickselected);
                ImageView imageView2 = (ImageView) getView().findViewById(R.id.imgGrass);
                imageView2.setImageResource(R.drawable.tick);
                fieldInfo.type  = "Clay";
            }
        });

        b.setOnClickListener(this);
        addImage.setOnClickListener(this);

        new AsyncTask<String, String, ArrayList<String>>() {

            @Override
            protected ArrayList<String> doInBackground(String... params) {
                try {

                    String response = makePostRequest("http://192.169.138.14:4000/api/teams/getCities",null, getActivity(), "GET");

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
                 spn_city = (Spinner) view.findViewById(R.id.ed_city);


                SpinnerAdapter adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, worldlist);


                // Spinner adapter
                spn_city.setAdapter(new ArrayAdapter<String>(getActivity(),
                               android.R.layout.simple_spinner_dropdown_item,
                               worldlist));
                spn_city.setAdapter(adap);
                // Spinner on item click listener
                spn_city
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
            }


        }.execute("");
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:

                fieldInfo.name = ed_name.getText().toString();
                fieldInfo.size = ed_size.getText().toString();
                fieldInfo.city = mCity_Id + "";
                fieldInfo.capacity = spn_acc.getSelectedItem().toString();

                FieldFacilities fragment = new FieldFacilities();
                Bundle args = new Bundle();
                CustomBinder oCustom = new CustomBinder();
                oCustom.setList(fieldInfo);
                args.putSerializable("complexObject", oCustom);
                fragment.setArguments(args);
                ((CreateFieldActivity) getActivity()).addFragment(fragment);
                break;

            case R.id.addImage:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
        }
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
        String android_id = Settings.Secure.getString(getActivity().getContentResolver(),
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //GETTING IMAGE FROM GALLERY
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = this.getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            profImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

    public boolean validation() {
        boolean b = true;
        if (ed_name.getText().toString().trim().equals("") || ed_name.getText().toString().trim().length()<1) {
            ed_name.setError(getString(R.string.invalidName));
            return false;
        } else {
            ed_name.setError(null);
        }

        if (ed_city.getText().toString().trim().equals("") || ed_city.getText().toString().trim().length()<1) {
            ed_city.setError(getString(R.string.invalidDistrictName));
            return false;
        } else {
            ed_city.setError(null);
        }

        if (ed_size.getText().toString().trim().equals("") || ed_size.getText().toString().trim().length()<1) {
            ed_size.setError(getString(R.string.invalidGroundSize));
            return false;
        } else {
            ed_size.setError(null);
        }


        return true;
    }

}
