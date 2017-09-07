package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.model.Custom;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;

import org.w3c.dom.Text;

/**
 * Created by tariq on 8/18/2017.
 */

public class FieldFacilities extends Fragment implements View.OnClickListener {

    TextView tv_water;
    TextView tv_parking;
    TextView tv_room;
    TextView tv_vest;
    TextView tv_wc;
    TextView tv_football;
    FieldInfo fieldInfo;
    CustomBinder oCustom;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         oCustom = (CustomBinder) getArguments().get("complexObject");
         fieldInfo = oCustom.getField();
        Log.i("asd",fieldInfo.name);
        View view = inflater.inflate(R.layout.fragment_facilities, container, false);
        View b = (View) view.findViewById(R.id.nextBtn);
        b.setOnClickListener(this);
        tv_water = (TextView) view.findViewById(R.id.tv_water);
        tv_parking = (TextView) view.findViewById(R.id.tv_parking);
        tv_football = (TextView) view.findViewById(R.id.tv_football);
        tv_vest = (TextView) view.findViewById(R.id.tv_vests);
        tv_room = (TextView) view.findViewById(R.id.tv_change_room);
        tv_wc = (TextView) view.findViewById(R.id.tv_wc);

        tv_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fieldInfo.water = fieldInfo.water == 0 ? 1 : 0;
                int imgResource;
                if (fieldInfo.water==0)
                    imgResource   = R.drawable.tick;
                else
                 imgResource = R.drawable.tickselected;

                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                i.setImageResource(imgResource);

            }

        });
        tv_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fieldInfo.parking = fieldInfo.parking == 0 ? 1 : 0;
                int imgResource;
                if (fieldInfo.parking==0)
                    imgResource   = R.drawable.tick;
                else
                    imgResource = R.drawable.tickselected;
                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);

                i.setImageResource(imgResource);

            }

        });

        tv_football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                int imgResource = R.drawable.tickselected;
                i.setImageResource(imgResource);
                fieldInfo.football = fieldInfo.football == 0 ? 1 : 0;
            }

        });
        tv_vest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                int imgResource = R.drawable.tickselected;
                i.setImageResource(imgResource);
                fieldInfo.vests = fieldInfo.vests == 0 ? 1 : 0;
            }

        });
        tv_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                int imgResource = R.drawable.tickselected;
                i.setImageResource(imgResource);
                fieldInfo.change_room = fieldInfo.change_room == 0 ? 1 : 0;
            }

        });
        tv_wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                int imgResource = R.drawable.tickselected;
                i.setImageResource(imgResource);
                fieldInfo.wc = fieldInfo.wc == 0 ? 1 : 0;
            }

        });
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                MapFragment fragment = new MapFragment();
                Bundle args = new Bundle();
                CustomBinder oCustom = new CustomBinder();
                oCustom.setList(fieldInfo);
                args.putSerializable("complexObject", oCustom);
                fragment.setArguments(args);
                ((CreateFieldActivity) getActivity()).addFragment(fragment);
                break;
        }
    }

//    void addFragment(Fragment f){
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_layout, f);
//        fragmentTransaction.commit();
//    }
}