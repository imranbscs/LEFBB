package com.laeb.laebproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.laeb.laebproject.general.Prefs;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;



public class VerificationCodeActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    EditText et_Code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        et_Code = (EditText) findViewById(R.id.ed_number) ;
        getSupportActionBar().hide();
    }

    public void submitClicked(View view) {

        Intent i = getIntent();
        final String code = et_Code.getText().toString();
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
                    Log.i("asd", "---------------- this is response : " + response);
                    String token = reader.getString("token");
                    String user = reader.getString("user");
                    if (!response.contains("ServerInvalidVerificationCode")) {
                        Intent i = new Intent(getBaseContext(), HomeActivity.class);
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("token", token);
                        Prefs.putString(getBaseContext(), Prefs.CREATE_TEAM, "Create Your Team");
                        Log.v("tog", token);
                       // editor.putString("user",user);
                        i.putExtra("user",user);
                        Prefs.putString(getBaseContext(), Prefs.auth_key, token);
                        Log.v("poi", Prefs.getString(getBaseContext(), Prefs.auth_key) + "===" + token);
                        editor.commit();
                        startActivity(i);
                        Log.i("asd", "---------------- this is response : " + code);
                        return "Success";
                    }
                    else
                    {
                        return "Fail";
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return "";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                TextView tvError = (TextView) findViewById(R.id.tvError);
                if (s != "Success")
                tvError.setVisibility(View.VISIBLE);
                else
                    tvError.setVisibility(View.INVISIBLE);
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
        uc.setRequestProperty("x-access-key", "ADBB-6CA3-15AE-359E");
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

    }
}
