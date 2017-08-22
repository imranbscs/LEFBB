package com.laeb.laebproject.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.laeb.laebproject.MenuActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.adapters.FootBallFieldsAdapter;
import com.laeb.laebproject.model.City;
import com.laeb.laebproject.model.UpComingGames;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tariq on 8/17/2017.
 */

public class FragmentProfile extends Fragment {
    EditText Edt_Full_Name;
    EditText Edt_Email;
    EditText Edt_DOB;
    DatePickerDialog datePickerDialog;
    JSONArray jsonarray;
    ArrayList<City> cities;
    ArrayList<String> worldlist;
    TextView SaveProfile;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.full_profile, container, false);
        TextView saveprofile = (TextView) v.findViewById(R.id.tv_save);
        saveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = Edt_Full_Name.getText().toString();

                String mEmail = Edt_Email.getText().toString();
                String mDOB = Edt_DOB.getText().toString();

                HashMap<String, String> param = new HashMap<String, String>();
                param.put("name", mName);
                param.put("image", "base64image");
                param.put("nickname", mEmail);
                param.put("city", "3");
                param.put("dob", "1986-04-13");
                param.put("gender", "M");
                param.put("place_of_birth", "M");
                param.put("height", "22");
                param.put("weight", "75");

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