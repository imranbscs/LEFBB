package com.laeb.laebproject.adapter_team;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laeb.laebproject.R;
import com.laeb.laebproject.model_create_team.team_schedule.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tariq on 9/11/2017.
 */

public class AdapterSchedule extends RecyclerView.Adapter<AdapterSchedule.ViewHolder> {

    List<Datum> listItems;
    public Context context;
    public static String j;

    public AdapterSchedule(List<Datum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public AdapterSchedule.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_your_schedule, parent, false);
        return new AdapterSchedule.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSchedule.ViewHolder holder, int position) {
        Datum obj = listItems.get(position);
        int a = position;
        holder.team1.setText(obj.getTeamA().toString());
        holder.team2.setText(obj.getTeamB().toString());
        holder.fieldName.setText(obj.getFieldName().toString());
        holder.dateTime.setText(obj.getDatetime().toString());
        holder.refree.setText(obj.getReferee().toString());
        Picasso.with(context).load(obj.getTeamALogo()).into(holder.img1);
        Picasso.with(context).load(obj.getTeamBLogo()).into(holder.img2);

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView team1, team2, fieldName, dateTime, refree;
        public ImageView img1, img2;

        public ViewHolder(View itemView) {
            super(itemView);
            team1 = (TextView) itemView.findViewById(R.id.team_name1);
            team2 = (TextView) itemView.findViewById(R.id.team_name2);
            fieldName = (TextView) itemView.findViewById(R.id.fieldName);
            dateTime = (TextView) itemView.findViewById(R.id.date_time);
            refree = (TextView) itemView.findViewById(R.id.refree);
            img1 = (ImageView) itemView.findViewById(R.id.flag1);
            img2 = (ImageView) itemView.findViewById(R.id.flag2);
        }
    }
}