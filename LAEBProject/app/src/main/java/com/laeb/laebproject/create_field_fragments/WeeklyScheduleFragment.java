package com.laeb.laebproject.create_field_fragments;

import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import android.provider.Settings;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


import com.laeb.laebproject.R;
import com.laeb.laebproject.expendible_list.CustomExpandableListAdapter;
import com.laeb.laebproject.expendible_list.ExpandableListDataPump;
import com.laeb.laebproject.model.Custom;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.Days;
import com.laeb.laebproject.model.FieldInfo;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.laeb.laebproject.R;
import com.laeb.laebproject.expendible_list.CustomExpandableListAdapter;
import com.laeb.laebproject.expendible_list.ExpandableListDataPump;
import com.laeb.laebproject.testjson.TestStaticMethod;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Created by tariq on 8/21/2017.
 */

public class WeeklyScheduleFragment extends Fragment implements View.OnClickListener {


    ExpandableListView expandableListView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    CustomBinder oCustom;
    FieldInfo fieldInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.field_weekly_schedule, container, false);

        oCustom = (CustomBinder) getArguments().get("complexObject");
        fieldInfo = oCustom.getField();
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);

        TextView aaa = (TextView) view.findViewById(R.id.ccc);
        aaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Hello "+expandableListAdapter.expandableListDetail,Toast.LENGTH_SHORT).show();
            }
        });

        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        View footerView = View.inflate(getActivity(), R.layout.btn_footer, null);
        View b = (View) footerView.findViewById(R.id.btnNext);
        b.setOnClickListener(this);
        expandableListView.addFooterView(footerView);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                String json = gson.toJson(TestStaticMethod.getAll());
                Log.v("ppp", "====== "+json);
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                String temp = "1600-1800-25";
//                TestStaticMethod.getMonday().add(temp);
//                expandableListDetail = ExpandableListDataPump.getData();
//                //invalidateViews();
//
//                expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
//                expandableListView.setAdapter(expandableListAdapter);
                //expandableListAdapter;
                return false;
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                Gson gson = new Gson();
                String json = gson.toJson(TestStaticMethod.getAll());
                Log.v("ppp", "====== "+json);
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

                fieldInfo.city = "1";
                fieldInfo.nearby = "E11";
                String s = gson.toJson(fieldInfo);
                final RequestParams paramss = new RequestParams();
                paramss.put("fieldInfo", s);
                paramss.put("pictures", " [{\"image\":\"base64string1\"},{\"image\":\"base64string3\"}]");
                paramss.put("operating", "{\"sunday\":{\"available\":1,\"slots\":[{\"from\":\"1600\",\"to\":\"1700\",\"rate\":\"50\"},{\"from\":\"1700\",\"to\":\"1800\",\"rate\":\"60\"},{\"from\":\"1800\",\"to\":\"2000\",\"rate\":\"200\"},{\"from\":\"2000\",\"to\":\"2100\",\"rate\":\"100\"}]},\n" +
                        "\"monday\":{\"available\":1,\"slots\":[{\"from\":\"1600\",\"to\":\"1700\",\"rate\":\"50\"},{\"from\":\"1700\",\"to\":\"1800\",\"rate\":\"60\"},{\"from\":\"1800\",\"to\":\"2000\",\"rate\":\"200\"},{\"from\":\"2000\",\"to\":\"2100\",\"rate\":\"100\"}]},\n" +
                        "\"tuesday\":{\"available\":0,\"slots\":[{\"from\":\"1600\",\"to\":\"1700\",\"rate\":\"50\"},{\"from\":\"1700\",\"to\":\"1800\",\"rate\":\"60\"},{\"from\":\"1800\",\"to\":\"2000\",\"rate\":\"200\"},{\"from\":\"2000\",\"to\":\"2100\",\"rate\":\"100\"}]},\n" +
                        "\"wednesday\":{\"available\":1,\"slots\":[{\"from\":\"1600\",\"to\":\"1700\",\"rate\":\"50\"},{\"from\":\"1700\",\"to\":\"1800\",\"rate\":\"60\"},{\"from\":\"1800\",\"to\":\"2000\",\"rate\":\"200\"},{\"from\":\"2000\",\"to\":\"2100\",\"rate\":\"100\"}]},\n" +
                        "\"thursday\":{\"available\":1,\"slots\":[{\"from\":\"1600\",\"to\":\"1700\",\"rate\":\"50\"},{\"from\":\"1700\",\"to\":\"1800\",\"rate\":\"60\"},{\"from\":\"1800\",\"to\":\"2000\",\"rate\":\"200\"},{\"from\":\"2000\",\"to\":\"2100\",\"rate\":\"100\"}]},\n" +
                        "\"friday\":{\"available\":1,\"slots\":[{\"from\":\"1600\",\"to\":\"1700\",\"rate\":\"50\"},{\"from\":\"1700\",\"to\":\"1800\",\"rate\":\"60\"},{\"from\":\"1800\",\"to\":\"2000\",\"rate\":\"200\"},{\"from\":\"2000\",\"to\":\"2100\",\"rate\":\"100\"}]},\n" +
                        "\"saturday\":{\"available\":1,\"slots\":[{\"from\":\"1600\",\"to\":\"1700\",\"rate\":\"50\"},{\"from\":\"1700\",\"to\":\"1800\",\"rate\":\"60\"},{\"from\":\"1800\",\"to\":\"2000\",\"rate\":\"200\"},{\"from\":\"2000\",\"to\":\"2100\",\"rate\":\"100\"}]}}");
                Log.i("asd", "---------------- this is response : " +  paramss.toString());
                new AsyncTask<String, String, String>() {

                    @Override
                    protected String doInBackground(String... params) {
                        try {

                            String response = makePostRequest("http://192.169.138.14:4000/api/fields/add",
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
                        Log.i("asd", "---------------- this is response : " + s);
                    }


                }.execute("");
                Log.i("asd", s);

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
            Log.i("asd", "Connected");
            writer.write(payload);
            Log.i("asd", "Connected");
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
            Log.i("asd", "error");
            ex.printStackTrace();
        }

        uc.disconnect();
        return jsonString.toString();
    }

}