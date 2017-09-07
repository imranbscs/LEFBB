package com.laeb.laebproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laeb.laebproject.R;
import com.laeb.laebproject.model.FieldInfo;

import java.util.List;

/**
 * Created by tariq on 8/17/2017.
 */

public class FootBallFieldsAdapter extends RecyclerView.Adapter<FootBallFieldsAdapter.ViewHolder> {

    List<FieldInfo> listItems;
    public Context context;

    public FootBallFieldsAdapter(List<FieldInfo> listItems, Context context) {

        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public FootBallFieldsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_football_fields, parent, false);
        return new FootBallFieldsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FootBallFieldsAdapter.ViewHolder holder, int position) {
        FieldInfo listItem = listItems.get(position);
        final int a = position;

        holder.nameTeam.setText(listItem.name);
        holder.address.setText( listItem.city + "    lat:" + listItem.latitude + "   long:" + listItem.longitude);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTeam;
        public TextView address;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTeam = (TextView) itemView.findViewById(R.id.txtName);
            address = (TextView) itemView.findViewById(R.id.txtAddress);
            //gameTime = (TextView) itemView.findViewById(R.id.dateTime);
        }
    }
}