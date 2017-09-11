package com.laeb.laebproject.adapter_team;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laeb.laebproject.R;
import com.laeb.laebproject.model_create_team.recived_invi.Datum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tariq on 9/11/2017.
 */

public class AdapterSabRecivedInvitation extends RecyclerView.Adapter<AdapterSabRecivedInvitation.ViewHolder> {

    List<Datum> listItems;
    public Context context;
    public static String j;

    public AdapterSabRecivedInvitation(List<Datum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public AdapterSabRecivedInvitation.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recived_invitation, parent, false);
        return new AdapterSabRecivedInvitation.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSabRecivedInvitation.ViewHolder holder, int position) {
        Datum obj = listItems.get(position);
        int a = position;
        Date date = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            date = fmt.parse(obj.getDatetime().toString());
        } catch (ParseException e) {e.printStackTrace();

        }

        holder.textBtnLay.setVisibility(View.VISIBLE);
        holder.accept_reciveBtnLay.setVisibility(View.GONE);

        if(date != null) {
            String stringDate = DateFormat.getTimeInstance().format(date);
            holder.time.setText(stringDate);

            SimpleDateFormat outFormat = new SimpleDateFormat("MMMM");
            String mon = outFormat.format(date);
            holder.month.setText(mon);

            SimpleDateFormat outFormatD = new SimpleDateFormat("dd");
            String d = outFormatD.format(date);
            holder.day.setText(d);
        }
        holder.capasity.setText(obj.getCapacity().toString()+"x"+obj.getCapacity().toString());
        holder.clubname.setText(obj.getTeamName().toString());
        holder.field.setText(obj.getFieldName()+" "+obj.getCity().toString());

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView day, month, time, capasity, field, clubname;
        public ImageView img1, img2;
        public LinearLayout accept_reciveBtnLay, textBtnLay;

        public ViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.dateDay);
            month = (TextView) itemView.findViewById(R.id.month);
            time = (TextView) itemView.findViewById(R.id.time);
            capasity = (TextView) itemView.findViewById(R.id.std_size);
            field = (TextView) itemView.findViewById(R.id.std_name);
            clubname = (TextView) itemView.findViewById(R.id.clubname);
            accept_reciveBtnLay = (LinearLayout) itemView.findViewById(R.id.btn_Ly);
            textBtnLay = (LinearLayout) itemView.findViewById(R.id.btn_text);
        }
    }
}