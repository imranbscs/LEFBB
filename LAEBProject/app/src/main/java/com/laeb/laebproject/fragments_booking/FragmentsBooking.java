package com.laeb.laebproject.fragments_booking;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laeb.laebproject.BookingActivity;
import com.laeb.laebproject.R;

import java.util.Calendar;
import java.util.Date;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by tariq on 8/26/2017.
 */

public class FragmentsBooking extends Fragment {
    int capacity;
    String d;
    String start;

    //capacity = 6 , date = 2017-08-04, start = 19:00
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.booking_animation, container, false);
        log.i("asd", "enter");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 0); //minus number would decrement the days

        for (int i = 1; i <= 14; i += 2) {
            String monthString = (String) DateFormat.format("MMM", cal.getTime()); // Jun
            String monthNumber = (String) DateFormat.format("dd", cal.getTime()); // 06
            int resID = getResources().getIdentifier("lbl_viwDate" + i, "id", getActivity().getPackageName());
            TextView lbl = (TextView) v.findViewById(resID);
            lbl.setText(monthString);
            int newi = i + 1;
            resID = getResources().getIdentifier("lbl_viwDate" + newi, "id", getActivity().getPackageName());
            TextView lbl2 = (TextView) v.findViewById(resID);
            lbl2.setText(monthNumber);
            cal.setTime(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }


        TextView searchTv = (TextView) v.findViewById(R.id.search_text);
        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentSearchFacilities fragment = new FragmentSearchFacilities();
                Bundle bundle = new Bundle();
                log.i("asd", capacity + " "  + d + " " + start);
                bundle.putInt("capacity", 0);
                bundle.putString("date", "20170827");
                bundle.putString("start", "1600");
                fragment.setArguments(bundle);

                ((BookingActivity) getActivity()).addFragment(fragment);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView img5 = (ImageView) view.findViewById(R.id.img_pitch5);

        img5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                log.i("asd", "7");
                capacity = 7;

            }
        });
        ImageView img6 = (ImageView) view.findViewById(R.id.img_pitch6);

        img6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                log.i("asd", "8");
                capacity = 8;

            }
        });
        ImageView img7 = (ImageView) view.findViewById(R.id.img_pitch7);

        img7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                log.i("asd", "9");
                capacity = 9;

            }
        });
        ImageView img8 = (ImageView) view.findViewById(R.id.img_pitch8);

        img8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                log.i("asd", "10");
                capacity = 10;

            }
        });
        ImageView img9 = (ImageView) view.findViewById(R.id.img_pitch9);

        img9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                log.i("asd", "11");
                capacity = 11;

            }
        });

        for (int i = 1; i <= 7; i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int newi = i - 1;
            cal.add(Calendar.DATE, newi);
            int resID = getResources().getIdentifier("imgViwdate" + i, "id", getActivity().getPackageName());
            ImageView img = (ImageView) view.findViewById(resID);
            img.setTag(cal.getTime());
            img.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    log.i("asd", v.getTag() + "");
                    d =  v.getTag().toString();


                }
            });
        }
        for (int i = 1; i <= 6; i++) {
            int resID = getResources().getIdentifier("txtTime" + i, "id", getActivity().getPackageName());
            TextView txt = (TextView) view.findViewById(resID);

            txt.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    TextView t = (TextView) v;
                    log.i("asd", t.getText() + "");
                    start= t.getText().toString();


                }
            });
        }

    }

}