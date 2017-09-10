package com.laeb.laebproject.adapter_team;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laeb.laebproject.R;
import com.laeb.laebproject.model_create_team.Datum;
import com.laeb.laebproject.model_create_team.ListInvite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tariq on 8/28/2017.
 */

public class AdapterInvitePlayer extends RecyclerView.Adapter<AdapterInvitePlayer.ViewHolder> {

    List<Datum> listItems;
    public Context context;
    public static List<ListInvite> invitedList;
    public static HashMap<String, List<Integer>> listHash;
    public static String j;


    public AdapterInvitePlayer(List<Datum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        listHash = new HashMap<>();
        invitedList = new ArrayList<>();
    }

    @Override
    public AdapterInvitePlayer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_friend_list_item, parent, false);
       //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ztest, parent, false);
        return new AdapterInvitePlayer.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterInvitePlayer.ViewHolder holder, int position) {
        Datum listItem = listItems.get(position);
        int a = position;
        holder.name.setText(listItem.getName());
        holder.role.setText(listItem.getPlayingRole());
        holder.ratingBar.setRating(1);
       // holder.pic
        Picasso.with(context).load(listItem.getPicture()).into(holder.pic);
        Log.v("ww",listItem.getStars().toString());
        if(!(listItem.getStars().equals(""))) {
            //float star = (float) listItem.getStars();
            int qwa = Integer.parseInt(listItem.getStars().toString());
            Float b = (float)qwa;
            holder.ratingBar.setRating(b);
        }
        final Datum tempDatum = listItem;
        holder.ratingValue.setText(listItem.getStars().toString());
        holder.inviteLayout.setBackgroundColor(Color.parseColor("#EDFCEA"));
        holder.inText.setText("INVITE");
//        holder.inText.setTextColor(Color.parseColor("#000"));

        holder.inviteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListInvite listInvite = new ListInvite();
                listInvite.setPlayerId(tempDatum.getUserId());
                invitedList.add(listInvite);
//                listHash.put("player_id", tempDatum.getUserId());
                holder.inviteLayout.setBackgroundColor(Color.parseColor("#90BE47"));
//                holder.inText.setTextColor(Color.parseColor("#fff"));
                holder.inText.setText("Invited");
                holder.tick_layout.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, inviteText;
        public TextView role;
        public TextView gameTime;
        public ImageView pic;
        public LinearLayout linearLayout;
        public RatingBar ratingBar;
        public TextView ratingValue;
        private RelativeLayout inviteLayout, tick_layout;
        public TextView inText;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.playername);
            role = (TextView) itemView.findViewById(R.id.role);
            pic = (ImageView) itemView.findViewById(R.id.playerpic);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            ratingValue = (TextView) itemView.findViewById(R.id.rating_num);
            inviteLayout = (RelativeLayout) itemView.findViewById(R.id.invitely);
            inText = (TextView) itemView.findViewById(R.id.inviteText);
            //inviteText = (TextView) itemView.findViewById(R.id.inviteText);
            tick_layout = (RelativeLayout) itemView.findViewById(R.id.tick_layout);
        }
    }

    public static String doneInvitation() {

//        Gson gson = new GsonBuilder().create();
//        JsonArray myCustomArray = gson.toJsonTree(listHash).getAsJsonArray();
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.add("player_id", myCustomArray);
//        String jsonStr = jsonObject.toString();


        //listHash.put("player_id", invitedList);

        Gson gson = new Gson();
        j = gson.toJson(invitedList);

//        if(!(validation())){
//            return;
//        }

        Log.v("jsonn", j);
        return j;
//
//        final ProgressDialog progressDialog =  new ProgressDialog(context);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/teams/invitePlayers", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.v("qwe", response);
//                progressDialog.dismiss();
//                Gson gson = new Gson();
//                SucessResponse sucessResponse = gson.fromJson(response, SucessResponse.class);
//                String _message = sucessResponse.messege;
//                int _status = sucessResponse.status;
//                Toast.makeText(context, "sucessful", Toast.LENGTH_LONG).show();
//                if(_status == 200){
//                    //startActivity(new Intent(getActivity(), InvitePlayerActivity.class));
//                }else {
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Log.v("wsx", "========   "+error+"");
//                Toast.makeText(context, "Unable to connect...", Toast.LENGTH_LONG).show();
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<>();
//                headers.put("x-access-key", Globels.ACCESS_KEY);
//                headers.put("x-access-token", Prefs.getString(context, Prefs.auth_key));
//                headers.put("locale", Globels.LOCAL);
//                headers.put("Content-Type", Globels.CONTENT_TYPE);
//                return headers;
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                return params;
//            }
//        };;
//
//        requestQueue.add(stringRequest);
    }

}