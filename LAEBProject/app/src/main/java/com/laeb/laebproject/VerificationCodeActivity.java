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


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
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
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;


public class VerificationCodeActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        getSupportActionBar().hide();
    }

    public void submitClicked(View view) {
<<<<<<< HEAD
        Intent i = getIntent();
        final String code = i.getStringExtra("code");
        final String phone = i.getStringExtra("phone");
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("phone", phone);
                param.put("code", code);
                RequestParams paramss = new RequestParams(param);

                try {
                    String response = makePostRequest("http://192.169.138.14:4000/api/users/verifyCode",
                            paramss.toString(), getApplicationContext());
                    JSONObject reader = new JSONObject(response);
                    String token = reader.getString("token");
                    if (!token.equals("")) {
                        Intent i = new Intent(getBaseContext(), MainMenuActivity.class);
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("token", token);
                        editor.commit();
                        startActivity(i);
                        Log.i("asd", "---------------- this is response : " + code);
                        return "Success";
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return "";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return "";
            }

        }.execute("");


    }

    public String makePostRequest(String stringUrl, String payload,
                                  Context context) throws IOException {
        Log.i("asd", "PayLoad" + payload);
        URL url = new URL(stringUrl);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();

        String line;
        StringBuffer jsonString = new StringBuffer();
        uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
=======
        startActivity(new Intent(this, ProfileActivity.class));
>>>>>>> e52924ad69e03bc8c748464b559ad277fb436f4d
    }
}
