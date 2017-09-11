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
    public static String j;


    public AdapterInvitePlayer(List<Datum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        invitedList = new ArrayList<>();
    }

    @Override
    public AdapterInvitePlayer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_friend_list_item, parent, false);
       //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ztest, parent, false);
        return new AdapterInvitePlayer.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterInvitePlayer.ViewHolder holder, final int position) {
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

        if(listItems.get(position).isSlected == true){
            holder.inviteLayout.setBackgroundColor(Color.parseColor("#90BE47"));
//                holder.inText.setTextColor(Color.parseColor("#fff"));
            holder.inText.setText("Invited");
            holder.tick_layout.setVisibility(View.VISIBLE);
        }else {
            holder.inText.setText("Invite");
            holder.tick_layout.setVisibility(View.GONE);
        }

        holder.inviteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListInvite listInvite = new ListInvite();
                listInvite.setPlayerId(tempDatum.getUserId());
                invitedList.add(listInvite);
                listItems.get(position).isSlected = true;
                holder.inviteLayout.setBackgroundColor(Color.parseColor("#90BE47"));
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
            tick_layout = (RelativeLayout) itemView.findViewById(R.id.tick_layout);
        }
    }

    public static String doneInvitation() {

        Gson gson = new Gson();
        j = gson.toJson(invitedList);
        Log.v("jsonn", j);
        return j;

    }

}