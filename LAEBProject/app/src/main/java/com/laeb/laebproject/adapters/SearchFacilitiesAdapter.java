package com.laeb.laebproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laeb.laebproject.R;
import com.laeb.laebproject.model.UpComingGames;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by tariq on 8/26/2017.
 */

public class SearchFacilitiesAdapter extends RecyclerView.Adapter<SearchFacilitiesAdapter.ViewHolder> {

    List<Hashtable<String, String>> listItems;
    public Context context;

    public SearchFacilitiesAdapter(List<Hashtable<String, String>> listItems, Context context) {
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
        Hashtable<String, String> ohash = listItems.get(position);
        Log.i("asd","abc");
        final int a = position;
        holder.nameTeam.setText(ohash.get("Name"));
        holder.pitch_type.setText(ohash.get("type"));

//        holder.cityName.setText(listItem.getGameLocation());
//        holder.gameTime.setText(listItem.getGameTime());
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
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTeam = (TextView) itemView.findViewById(R.id.textView3);
            //cityName = (TextView) itemView.findViewById(R.id.game_city);
           // gameTime = (TextView) itemView.findViewById(R.id.dateTime);
            pitch_type = (TextView) itemView.findViewById(R.id.pitch_type);

        }
    }
}