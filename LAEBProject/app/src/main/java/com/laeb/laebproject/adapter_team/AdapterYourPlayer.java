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

import com.laeb.laebproject.R;
import com.laeb.laebproject.model.UpComingGames;
import com.laeb.laebproject.model_create_team.Datum;
import com.laeb.laebproject.model_create_team.ListInvite;
import com.laeb.laebproject.model_create_team.your_player_model.InvitedPlayer;
import com.laeb.laebproject.model_create_team.your_player_model.SelectedPlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tariq on 8/28/2017.
 */

public class AdapterYourPlayer extends RecyclerView.Adapter<AdapterYourPlayer.ViewHolder> {

    List<Object> listItems;
    public Context context;
    public static List<ListInvite> invitedList;
    public static HashMap<String, List<Integer>> listHash;
    public static String j;

    public AdapterYourPlayer(List<Object> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        listHash = new HashMap<>();
        invitedList = new ArrayList<>();
    }

    @Override
    public AdapterYourPlayer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_friend_list_item, parent, false);
        return new AdapterYourPlayer.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterYourPlayer.ViewHolder holder, int position) {
        Object obj = listItems.get(position);
        int a = position;
        if(a<1){
            SelectedPlayer listItem = (SelectedPlayer)obj;
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

        public TextView name, inviteText;
        public TextView role;
        public TextView gameTime;
        public ImageView pic;
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
        }
    }
}