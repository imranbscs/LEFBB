package com.laeb.laebproject.adapter_team;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.laeb.laebproject.R;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.general.Prefs;
import com.laeb.laebproject.model_create_team.SucessResponse;
import com.laeb.laebproject.model_create_team.recived_invi.Datum;
import com.laeb.laebproject.model_create_team.recived_invi.RecivedInvitations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tariq on 9/11/2017.
 */

public class AdapterSabRecivedInvitation extends RecyclerView.Adapter<AdapterSabRecivedInvitation.ViewHolder> {

    List<Datum> listItems;
    public Context context;
    public static String j;
    int invitationId;

    public AdapterSabRecivedInvitation(List<Datum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public AdapterSabRecivedInvitation.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recived_invitation, parent, false);
        return new AdapterSabRecivedInvitation.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSabRecivedInvitation.ViewHolder holder, int position) {
        Datum obj = listItems.get(position);
        final int a = position;
        invitationId = obj.getInvitationId();
        Date date = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            date = fmt.parse(obj.getDatetime().toString());
        } catch (ParseException e) {e.printStackTrace();

        }

        holder.textBtnLay.setVisibility(View.VISIBLE);
        holder.accept_reciveBtnLay.setVisibility(View.GONE);

        if(date != null) {
            String stringDate = DateFormat.getTimeInstance().format(date);
            holder.time.setText(stringDate);

            SimpleDateFormat outFormat = new SimpleDateFormat("MMMM");
            String mon = outFormat.format(date);
            holder.month.setText(mon);

            SimpleDateFormat outFormatD = new SimpleDateFormat("dd");
            String d = outFormatD.format(date);
            holder.day.setText(d);
        }
        holder.capasity.setText(obj.getCapacity().toString()+"x"+obj.getCapacity().toString());
        holder.clubname.setText(obj.getTeamName().toString());
        holder.field.setText(obj.getFieldName()+" "+obj.getCity().toString());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptReject(1, a);
            }
        });
        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptReject(0, a);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView day, month, time, capasity, field, clubname;
        public ImageView img1, img2;
        public LinearLayout accept_reciveBtnLay, textBtnLay;
        LinearLayout decline, accept;

        public ViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.dateDay);
            month = (TextView) itemView.findViewById(R.id.month);
            time = (TextView) itemView.findViewById(R.id.time);
            capasity = (TextView) itemView.findViewById(R.id.std_size);
            field = (TextView) itemView.findViewById(R.id.std_name);
            clubname = (TextView) itemView.findViewById(R.id.clubname);
            accept_reciveBtnLay = (LinearLayout) itemView.findViewById(R.id.btn_Ly);
            textBtnLay = (LinearLayout) itemView.findViewById(R.id.btn_text);
            decline = (LinearLayout) itemView.findViewById(R.id.decline);
            accept = (LinearLayout) itemView.findViewById(R.id.accept);
        }
    }

    public void acceptReject(int a, int inn){
        final int b = a;
        final int index = inn;
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/match/requestResponded", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                SucessResponse sucessResponse = gson.fromJson(response, SucessResponse.class);
                int _status = sucessResponse.status;

                if (_status == 200) {
                    listItems.remove(index);
                    notifyDataSetChanged();
                    if(b==1){
                        Toast.makeText(context, "Accepted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    listItems.remove(index);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Accepted", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.v("wsx", "========   " + error + "");
                Toast.makeText(context, "Unable to connect...", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-access-key", Globels.ACCESS_KEY);
                headers.put("x-access-token", Prefs.getString(context, Prefs.auth_key));
                headers.put("locale", Globels.LOCAL);
                headers.put("Content-Type", Globels.CONTENT_TYPE);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("status", b+"");
                params.put("invitation_id", invitationId+"");
                return params;
            }
        };
        ;

        requestQueue.add(stringRequest);
    }

}