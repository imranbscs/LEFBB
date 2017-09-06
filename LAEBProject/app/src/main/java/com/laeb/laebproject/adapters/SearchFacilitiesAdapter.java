package com.laeb.laebproject.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laeb.laebproject.BookingActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.booking_models.Datum;
import com.laeb.laebproject.fragments_booking.FragmentMap;
import com.laeb.laebproject.fragments_booking.FragmentPaymentChoice;
import com.laeb.laebproject.fragments_booking.FragmentScratch;
import com.laeb.laebproject.fragments_booking.FragmentSearchFacilities;
import com.laeb.laebproject.model.UpComingGames;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by tariq on 8/26/2017.
 */

public class SearchFacilitiesAdapter extends RecyclerView.Adapter<SearchFacilitiesAdapter.ViewHolder> {

    List<Datum> listItems;
    public Context context;

    public SearchFacilitiesAdapter(List<Datum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public SearchFacilitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_ground_item, parent, false);
        return new SearchFacilitiesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchFacilitiesAdapter.ViewHolder holder, int position) {
        //UpComingGames listItem = listItems.get(position);
        Datum listItem = listItems.get(position);
        Log.i("asd","abc");
        final int a = position;
//        holder.nameTeam.setText(ohash.get("Name"));
//        holder.pitch_type.setText(ohash.get("type"));

        holder.nameTeam.setText(listItem.getName());
        holder.pitch_type.setText(listItem.getType());
        holder.cityName.setText(listItem.getCity());
        if (listItem.getFloodlights() == 1)
            holder.imgLights.setVisibility(View.VISIBLE);
        else
            holder.imgLights.setVisibility(View.INVISIBLE);
        if (listItem.getChangeRoom() == 1)
            holder.imgToilets.setVisibility(View.VISIBLE);
        else
            holder.imgToilets.setVisibility(View.INVISIBLE);
        if (listItem.getParking() == 1)
            holder.imgParking.setVisibility(View.VISIBLE);
        else
            holder.imgParking.setVisibility(View.INVISIBLE);
        if (listItem.getFootball() == "1")
            holder.imgParking.setVisibility(View.VISIBLE);
        else
            holder.imgParking.setVisibility(View.INVISIBLE);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentMap fragment = new FragmentMap();
                ((BookingActivity) context).addFragment(fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTeam;
        public TextView cityName;
        public TextView gameTime;
        public TextView pitch_type;
        public ImageView imgParking;
        public ImageView imgLights;
        public ImageView imgToilets;
        public ImageView imgFootball;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTeam = (TextView) itemView.findViewById(R.id.textView3);
            cityName = (TextView) itemView.findViewById(R.id.address);
            imgParking = (ImageView) itemView.findViewById(R.id.imgparking);
            imgLights = (ImageView) itemView.findViewById(R.id.imglight);
            imgToilets = (ImageView) itemView.findViewById(R.id.imgtoilet);
            imgFootball = (ImageView) itemView.findViewById(R.id.imgfootball);
           // gameTime = (TextView) itemView.findViewById(R.id.dateTime);
            pitch_type = (TextView) itemView.findViewById(R.id.pitch_type);

        }
    }
}