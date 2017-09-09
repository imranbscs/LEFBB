package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;

/**
 * Created by tariq on 8/18/2017.
 */

public class FieldFacilities extends Fragment implements View.OnClickListener {

    TextView tv_water;
    TextView tv_parking;
    TextView tv_room;
    TextView tv_vest;
    TextView tv_wc, floodLight;
    TextView tv_football;
    FieldInfo fieldInfo;
    EditText capacity;
    CustomBinder oCustom;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facilities, container, false);

        oCustom = (CustomBinder) getArguments().get("complexObject");
        if (oCustom !=null)
        fieldInfo = oCustom.getField();
        Log.i("asd",fieldInfo.name);

        View b = (View) view.findViewById(R.id.nextBtn);
        b.setOnClickListener(this);
        tv_water = (TextView) view.findViewById(R.id.tv_water);
        tv_parking = (TextView) view.findViewById(R.id.tv_parking);
        tv_football = (TextView) view.findViewById(R.id.tv_football);
        tv_vest = (TextView) view.findViewById(R.id.tv_vests);
        tv_room = (TextView) view.findViewById(R.id.tv_change_room);
        tv_wc = (TextView) view.findViewById(R.id.tv_wc);
        floodLight = (TextView) view.findViewById(R.id.tv_flood_light);
        capacity = (EditText) view.findViewById(R.id.tv_capacity);


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
//                ViewGroup container = (ViewGroup) view.getParent();
//                ImageView i = (ImageView) container.getChildAt(1);
//                int imgResource = R.drawable.tickselected;
//                i.setImageResource(imgResource);
                fieldInfo.football = fieldInfo.football == 0 ? 1 : 0;
                int imgResource;
                if (fieldInfo.football==0)
                    imgResource   = R.drawable.tick;
                else
                    imgResource = R.drawable.tickselected;

                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                i.setImageResource(imgResource);
            }

        });

        tv_vest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ViewGroup container = (ViewGroup) view.getParent();
//                ImageView i = (ImageView) container.getChildAt(1);
//                int imgResource = R.drawable.tickselected;
//                i.setImageResource(imgResource);
                fieldInfo.vests = fieldInfo.vests == 0 ? 1 : 0;
                int imgResource;
                if (fieldInfo.vests==0)
                    imgResource   = R.drawable.tick;
                else
                    imgResource = R.drawable.tickselected;

                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                i.setImageResource(imgResource);
            }

        });
        tv_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ViewGroup container = (ViewGroup) view.getParent();
//                ImageView i = (ImageView) container.getChildAt(1);
//                int imgResource = R.drawable.tickselected;
//                i.setImageResource(imgResource);
                fieldInfo.change_room = fieldInfo.change_room == 0 ? 1 : 0;
                int imgResource;
                if (fieldInfo.change_room==0)
                    imgResource   = R.drawable.tick;
                else
                    imgResource = R.drawable.tickselected;

                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                i.setImageResource(imgResource);
            }

        });
        tv_wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ViewGroup container = (ViewGroup) view.getParent();
//                ImageView i = (ImageView) container.getChildAt(1);
//                int imgResource = R.drawable.tickselected;
//                i.setImageResource(imgResource);
                fieldInfo.wc = fieldInfo.wc == 0 ? 1 : 0;
                int imgResource;
                if (fieldInfo.wc==0)
                    imgResource   = R.drawable.tick;
                else
                    imgResource = R.drawable.tickselected;

                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                i.setImageResource(imgResource);
            }

        });

        floodLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ViewGroup container = (ViewGroup) view.getParent();
//                ImageView i = (ImageView) container.getChildAt(1);
//                int imgResource = R.drawable.tickselected;
//                i.setImageResource(imgResource);
                fieldInfo.light = fieldInfo.light == 0 ? 1 : 0;
                int imgResource;
                if (fieldInfo.light==0)
                    imgResource   = R.drawable.tick;
                else
                    imgResource = R.drawable.tickselected;

                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                i.setImageResource(imgResource);
            }

        });

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                if(capacity.getText().toString().length()<1){
                    //fieldInfo.capacity = "0";
                    Globels.CAPASITY = "0";
                }else{
                    //fieldInfo.capacity = capacity.getText().toString();
                    Globels.CAPASITY = capacity.getText().toString();
                }
                MapFragmentLeab fragment = new MapFragmentLeab();
                Bundle args = new Bundle();
                CustomBinder oCustom = new CustomBinder();
                oCustom.setList(fieldInfo);
                args.putSerializable("complexObject", oCustom);
                fragment.setArguments(args);
                ((CreateFieldActivity) getActivity()).addFragment(fragment);
                break;
        }
    }

}