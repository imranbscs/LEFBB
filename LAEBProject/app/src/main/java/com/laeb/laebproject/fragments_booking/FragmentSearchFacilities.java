package com.laeb.laebproject.fragments_booking;

import android.app.Fragment;
import android.app.job.JobInfo;
import android.content.Context;
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

import com.laeb.laebproject.AddNumberActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.adapters.SearchFacilitiesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * Created by tariq on 8/26/2017.
 */

public class FragmentSearchFacilities extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Hashtable<String, String>> listItems;
    SharedPreferences channel;
    private int cap;
    private String date;
    private String start;


    public FragmentSearchFacilities() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle b = getArguments();
        cap = (int) b.get("capacity");
        date = (String) b.get("date");
        start = (String) b.get("start");
        View v = inflater.inflate(R.layout.fragment_booking_search, container, false);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                try {

                    String response = makePostRequest("http://192.169.138.14:4000/api/Fields/searching",
                            "capacity=&date=" + date + "&start=" + start, getActivity(), "POST");
                    JSONObject reader = new JSONObject(response);
                    return response;
                } catch (IOException ex) {
                    ex.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String json) {
                Log.d("asd", "onPostExecute:asd");
                super.onPostExecute(json);
                try {
                    JSONObject JObj = new JSONObject(json);

                    JSONArray JArray = new JSONArray( JObj.get("data").toString());
                    Log.d("asd", "onPostExecute:asd");
                    listItems = new ArrayList<Hashtable<String, String>>();
                    for (int i = 0; i < JArray.length(); i++) {
                        Hashtable<String, String> ohash = new Hashtable<String, String>();

                        JSONObject j = JArray.getJSONObject(i);
                        ohash.put("Name", j.getString("name"));
                        Log.d("asd", "onPostExecute: " + j.getString("name"));
                        ohash.put("capacity", j.getString("capacity"));
                        ohash.put("type", j.getString("type"));
                        listItems.add(ohash);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listItems = new ArrayList<Hashtable<String, String>>();

                adapter = new SearchFacilitiesAdapter(listItems, getActivity());
                recyclerView.setAdapter(adapter);

            }


        }.execute("");

    }
}