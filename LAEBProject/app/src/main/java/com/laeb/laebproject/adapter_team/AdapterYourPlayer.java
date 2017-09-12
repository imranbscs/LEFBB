package com.laeb.laebproject.adapter_team;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
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
import com.laeb.laebproject.model.UpComingGames;
import com.laeb.laebproject.model_create_team.Datum;
import com.laeb.laebproject.model_create_team.ListInvite;
import com.laeb.laebproject.model_create_team.SucessResponse;
import com.laeb.laebproject.model_create_team.your_player_model.InvitedPlayer;
import com.laeb.laebproject.model_create_team.your_player_model.SelectedPlayer;
import com.laeb.laebproject.model_create_team.your_player_model.TeamYourPlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tariq on 8/28/2017.
 */

public class AdapterYourPlayer extends RecyclerView.Adapter<AdapterYourPlayer.ViewHolder> {

    List<Object> listItems;
    public Context context;
    public static List<ListInvite> invitedList;
    public static String j;
    int selectedPlayers;
    int squadID;

    public AdapterYourPlayer(List<Object> listItems, Context context, int selectedPlayers) {
        this.listItems = listItems;
        this.context = context;
        invitedList = new ArrayList<>();
        this.selectedPlayers = selectedPlayers;
    }

    @Override
    public AdapterYourPlayer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_friend_list_item, parent, false);
        return new AdapterYourPlayer.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterYourPlayer.ViewHolder holder, final int position) {
        Object obj = listItems.get(position);
        int a = position;
        if(a<selectedPlayers){
            SelectedPlayer listItem = (SelectedPlayer)obj;
            holder.name.setText(listItem.getName());
            holder.role.setText("");
            holder.ratingBar.setRating(1);
            squadID = listItem.getSquadId();
            Picasso.with(context).load(listItem.getPicture()).into(holder.pic);
            if(!(listItem.getStars().equals(""))) {
                //float star = (float) listItem.getStars();
                int qwa = Integer.parseInt(listItem.getStars().toString());
                Float b = (float)qwa;
                holder.ratingBar.setRating(b);
            }
            //final Datum tempDatum = listItem;
            holder.ratingValue.setText(listItem.getStars().toString());
            holder.inviteLayout.setVisibility(View.GONE);
            holder.selectedLy.setVisibility(View.VISIBLE);
        }else {
            InvitedPlayer listItem = (InvitedPlayer) obj;
            holder.name.setText(listItem.getName());
            holder.role.setText("");
            holder.ratingBar.setRating(1);
            Picasso.with(context).load(listItem.getPicture()).into(holder.pic);
            if(!(listItem.getStars().equals(""))) {
                //float star = (float) listItem.getStars();
                int qwa = Integer.parseInt(listItem.getStars().toString());
                Float b = (float)qwa;
                holder.ratingBar.setRating(b);
            }
            //final Datum tempDatum = listItem;
            holder.ratingValue.setText(listItem.getStars().toString());
            holder.selectedLy.setVisibility(View.GONE);
            holder.inviteLayout.setVisibility(View.VISIBLE);
            holder.inviteLayout.setBackgroundColor(Color.parseColor("#90BE47"));
            holder.inviteText.setText("INVITED");
            holder.inviteText.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePlayer(position);
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
        public ImageView pic, cross, tick;
        public LinearLayout linearLayout;
        public RatingBar ratingBar;
        public TextView ratingValue;
        public RelativeLayout inviteLayout;
        public TextView inText;
        public LinearLayout dummy, selectedLy;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.playername);
            role = (TextView) itemView.findViewById(R.id.role);
            pic = (ImageView) itemView.findViewById(R.id.playerpic);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            ratingValue = (TextView) itemView.findViewById(R.id.rating_num);
            inviteLayout = (RelativeLayout) itemView.findViewById(R.id.invitely);
            selectedLy = (LinearLayout) itemView.findViewById(R.id.selected);
            inText = (TextView) itemView.findViewById(R.id.inviteText);
            dummy = (LinearLayout) itemView.findViewById(R.id.dummylinear);
            dummy.setVisibility(View.GONE);
            inviteText = (TextView) itemView.findViewById(R.id.inviteText);
            cross = (ImageView) itemView.findViewById(R.id.cross);
            tick = (ImageView) itemView.findViewById(R.id.tick);
        }
    }

    public void deletePlayer(int i){
        final int a = i;
        final ProgressDialog progressDialog =  new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.169.138.14:4000/api/teams/v2/removePlayerFromSquad", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("qwe", response);
                progressDialog.dismiss();
                Gson gson = new Gson();
                SucessResponse sucessResponse = gson.fromJson(response, SucessResponse.class);
                int _status = sucessResponse.status;


                if(_status == 200){
                    listItems.remove(a);
                    selectedPlayers = selectedPlayers-1;
                    notifyDataSetChanged();
                }else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.v("wsx", "========   "+error+"");
                Toast.makeText(context, "Unable to connect...", Toast.LENGTH_LONG).show();
            }
        }){
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
                params.put("squad_id", squadID+"");
                return params;
            }
        };;

        requestQueue.add(stringRequest);
    }
}