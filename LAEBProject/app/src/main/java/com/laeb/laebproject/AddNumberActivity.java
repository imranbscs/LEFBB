package com.laeb.laebproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Topic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class AddNumberActivity extends AppCompatActivity {

    AmazonSNSClient snsClient;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);

        getSupportActionBar().hide();
    }

    public void continueClicked(View view) {
        EditText ctCode = (EditText) findViewById(R.id.ed_contrycode);
        EditText ctNumber = (EditText) findViewById(R.id.ed_number);
        phoneNumber = ctCode.getText().toString() + ctNumber.getText().toString();
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                try {
                    Log.i("asd", "---------------- this is response : " + phoneNumber);
                    String response = makePostRequest("http://192.169.138.14:4000/api/users/sendVerificationCode",
                            "phone=" + phoneNumber, getApplicationContext());
                    JSONObject reader = new JSONObject(response);
                    String code = reader.getString("code");
                    Log.i("asd", "---------------- this is response : " + code);
                    return code;
                } catch (IOException ex) {
                    ex.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String code) {
                super.onPostExecute(code);
                new AWSTask().execute(code);
            }


        }.execute("");


    }

    class AWSTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            try {
                AWSCredentials oCredentials = new AWSCredentials() {
                    @Override
                    public String getAWSAccessKeyId() {
                        return "aa";
                    }

                    @Override
                    public String getAWSSecretKey() {
                        return "aaa";
                    }
                };
                snsClient = new AmazonSNSClient(oCredentials);

                String message = "Your verification code is " + urls[0];

                Map<String, MessageAttributeValue> smsAttributes =
                        new HashMap<String, MessageAttributeValue>();
                smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                        .withStringValue("mySenderID") //The sender ID shown on the device.
                        .withDataType("String"));
                smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                        .withStringValue("0.01") //Sets the max price to 0.50 USD.
                        .withDataType("Number"));
                smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                        .withStringValue("Promotional") //Sets the type to promotional.
                        .withDataType("String"));
              //  PublishResult oresult = snsClient.publish(new PublishRequest()
                //       .withMessage(message)
               //         .withPhoneNumber(phoneNumber)
               //        .withMessageAttributes(smsAttributes));
             //   Log.i("ERR", oresult.toString());
                return urls[0];

            } catch (Exception e) {
                this.exception = e;
                Log.e("ABC", e.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String Code) {
            Intent i = new Intent(getBaseContext(), VerificationCodeActivity.class);
            i.putExtra("code", Code);
            i.putExtra("phone", phoneNumber);
            startActivity(i);
        }
    }

    public String makePostRequest(String stringUrl, String payload,
                                  Context context) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();

        String line;
        StringBuffer jsonString = new StringBuffer();

        uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
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
    }
}
