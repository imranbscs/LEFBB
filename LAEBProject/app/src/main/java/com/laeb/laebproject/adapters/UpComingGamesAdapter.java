package com.laeb.laebproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laeb.laebproject.R;
import com.laeb.laebproject.model.UpComingGames;

import java.util.List;

/**
 * Created by tariq on 8/17/2017.
 */

public class UpComingGamesAdapter extends RecyclerView.Adapter<UpComingGamesAdapter.ViewHolder> {

    List<UpComingGames> listItems;
    public Context context;

    public UpComingGamesAdapter(List<UpComingGames> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public UpComingGamesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_upcoming_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UpComingGamesAdapter.ViewHolder holder, int position) {
        UpComingGames listItem = listItems.get(position);
        final int a = position;
        holder.nameTeam.setText(listItem.getNameTeam());
        holder.cityName.setText(listItem.getGameLocation());
        holder.gameTime.setText(listItem.getGameTime());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTeam;
        public TextView cityName;
        public TextView gameTime;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTeam = (TextView) itemView.findViewById(R.id.team_name);
            cityName = (TextView) itemView.findViewById(R.id.game_city);
            gameTime = (TextView) itemView.findViewById(R.id.dateTime);

        }
    }
}