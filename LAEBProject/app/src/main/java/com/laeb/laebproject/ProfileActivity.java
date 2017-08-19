package com.laeb.laebproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
;

public class ProfileActivity extends AppCompatActivity {

     EditText Edt_Full_Name;
     EditText Edt_city;
     EditText Edt_Email;
     EditText Edt_DOB;


    TextView SaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Edt_Full_Name =(EditText) findViewById(R.id.ed_fumm_name);
        Edt_city =(EditText) findViewById(R.id.ed_city);
        Edt_Email =(EditText) findViewById(R.id.ed_email);
        Edt_DOB =(EditText) findViewById(R.id.ed_dob) ;

        SaveProfile=(TextView)findViewById(R.id.tv_saveProfile);


        SaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = Edt_Full_Name.getText().toString();
                String mCity = Edt_city.getText().toString();
                String mEmail = Edt_Email.getText().toString();
                String mDOB = Edt_DOB.getText().toString();
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("name", mName);
                param.put("picture", null);
                param.put("email", mEmail);
                param.put("city", mCity);
                param.put("dob", mDOB);
                param.put("gender", null);

                final RequestParams paramss = new RequestParams(param);

                new AsyncTask<String, String, String>() {

                    @Override
                    protected String doInBackground(String... params) {
                        try {

                            String response = makePostRequest("http://192.169.138.14:4000/api/profile/v2/basic",
                                    paramss.toString(),
                                    getApplicationContext());
                            Log.i("asd", "---------------- this is response : " + response);
                            return "Success";
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            return "";
                        }
                    }

                }.execute("");
                getSupportActionBar().hide();



            }
        });


    }

    public void saveClicked(View view) {
        startActivity(new Intent(this, FullProfileActivity.class));
    }

    public  String makePostRequest(String stringUrl, String payload,
                                   Context context) throws IOException {

        URL url = new URL(stringUrl);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();

        String line;
        StringBuffer jsonString = new StringBuffer();
        SharedPreferences channel=this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String strChannel=channel.getString("token","Default").toString();
        Log.i("asd", "---------------- this is response : " + strChannel);
        uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        uc.setRequestProperty("x-access-token", strChannel );
        uc.setRequestProperty("locale", "en");
        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        uc.setRequestProperty("x-access-key", "ADBB-6CA3-15AE-359E");
        // uc.setRequestProperty("device", android_id);
        uc.setRequestMethod("POST");
        uc.setDoInput(true);
        uc.setInstanceFollowRedirects(false);
        uc.connect();
        OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");
        writer.write(payload);
        writer.close();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while((line = br.readLine()) != null){
                jsonString.append(line);
                Log.i("asd",line);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        uc.disconnect();
        return jsonString.toString();
    }
}
