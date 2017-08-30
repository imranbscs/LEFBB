package com.laeb.laebproject.adapter_team;

import android.content.Context;
import android.renderscript.Long4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.laeb.laebproject.R;
import com.laeb.laebproject.adapters.ScheduleRefreeAdapter;
import com.laeb.laebproject.model.UpComingGames;
import com.laeb.laebproject.model_create_team.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariq on 8/28/2017.
 */

public class AdapterInvitePlayer extends RecyclerView.Adapter<AdapterInvitePlayer.ViewHolder> {

    List<Datum> listItems;
    public Context context;


    public AdapterInvitePlayer(List<Datum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public AdapterInvitePlayer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_friend_list_item, parent, false);
       //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ztest, parent, false);
        return new AdapterInvitePlayer.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterInvitePlayer.ViewHolder holder, int position) {
        Datum listItem = listItems.get(position);
        int a = position;
        holder.name.setText(listItem.getName());
        holder.role.setText(listItem.getPlayingRole());
       // holder.pic
        Picasso.with(context).load(listItem.getPicture()).into(holder.pic);
        Log.v("ww",listItem.getStars().toString());
        if(!(listItem.getStars().equals(""))) {
            //float star = (float) listItem.getStars();
            //int qwa = Integer.parseInt(listItem.getStars().toString());
            //Float b = (float)qwa;
            //holder.ratingBar.setRating(star);
        }
        //holder.gameTime.setText(listItem.getGameTime());
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

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.playername);
            role = (TextView) itemView.findViewById(R.id.role);
            pic = (ImageView) itemView.findViewById(R.id.playerpic);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }
    }
}