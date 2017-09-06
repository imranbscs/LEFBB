package com.laeb.laebproject.adapter_team;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tariq on 8/31/2017.
 */

public class AdapterYourTeam extends RecyclerView.Adapter<AdapterYourTeam.ViewHolder> {

    List<Datum> listItems;
    public Context context;
    public static List<ListInvite> invitedList;
    public static HashMap<String, List<Integer>> listHash;
    public static String j;


    public AdapterYourTeam(List<Datum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        listHash = new HashMap<String, List<Integer>>();
        invitedList = new ArrayList<>();
    }

    @Override
    public AdapterYourTeam.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_friend_list_item, parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ztest, parent, false);
        return new AdapterYourTeam.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterYourTeam.ViewHolder holder, int position) {
        Datum listItem = listItems.get(position);
        int a = position;
//        holder.name.setText(listItem.getName());
//        holder.role.setText(listItem.getPlayingRole());
//        holder.ratingBar.setRating(1);
//        // holder.pic
//        Picasso.with(context).load(listItem.getPicture()).into(holder.pic);
//        Log.v("ww", listItem.getStars().toString());
//        if (!(listItem.getStars().equals(""))) {
//            //float star = (float) listItem.getStars();
//            int qwa = Integer.parseInt(listItem.getStars().toString());
//            Float b = (float) qwa;
//            holder.ratingBar.setRating(b);
//        }
//        final Datum tempDatum = listItem;
//        holder.ratingValue.setText(listItem.getStars().toString());
//
//        holder.inviteLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ListInvite listInvite = new ListInvite();
//                listInvite.setPlayerId(tempDatum.getUserId());
//                invitedList.add(listInvite);
////                listHash.put("player_id", tempDatum.getUserId());
//                holder.inviteLayout.setBackgroundColor(Color.parseColor("#90BE47"));
//                holder.inText.setTextColor(Color.parseColor("#fff"));
//                holder.inText.setText("Invited");
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView role;
        public TextView gameTime;
        public ImageView pic;
        public LinearLayout linearLayout;
        public RatingBar ratingBar;
        public TextView ratingValue;
        RelativeLayout inviteLayout;
        public TextView inText;

        public ViewHolder(View itemView) {
            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.playername);
//            role = (TextView) itemView.findViewById(R.id.role);
//            pic = (ImageView) itemView.findViewById(R.id.playerpic);
//            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
//            ratingValue = (TextView) itemView.findViewById(R.id.rating_num);
//            inviteLayout = (RelativeLayout) itemView.findViewById(R.id.invitely);
//            inText = (TextView) itemView.findViewById(R.id.inviteText);
        }
    }

    public static String doneInvitation() {
        Gson gson = new Gson();
        j = gson.toJson(invitedList);
        Log.v("jsonn", j);
        return j;

    }

}