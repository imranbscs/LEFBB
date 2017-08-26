package com.laeb.laebproject.team_fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.laeb.laebproject.BookingActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.fragments_booking.FragmentSearchFacilities;

import java.util.ArrayList;

/**
 * Created by tariq on 8/26/2017.
 */

public class FragmentCreateTeam extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_createteam, container, false);
        LinearLayout next = (LinearLayout) v.findViewById(R.id.nextBtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog();
            }
        });
        return v;
    }

    public void addDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.color_dialog);
        dialog.show();
        TextView okBtn = (TextView) dialog.findViewById(R.id.done);

//        final String finalRateStr = rateStr;
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    dialog.dismiss();

            }
        });

    }

}