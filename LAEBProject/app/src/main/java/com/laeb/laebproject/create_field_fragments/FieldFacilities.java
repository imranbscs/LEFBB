package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.model.FieldInfo;

import org.w3c.dom.Text;

/**
 * Created by tariq on 8/18/2017.
 */

public class FieldFacilities extends Fragment implements View.OnClickListener {

    TextView tv_water;
    TextView tv_parking;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FieldInfo fieldInfo = (FieldInfo) savedInstanceState.get("complexObject");

        View view = inflater.inflate(R.layout.fragment_facilities, container, false);
        View b = (View) view.findViewById(R.id.nextBtn);
        b.setOnClickListener(this);
        tv_water = (TextView) view.findViewById(R.id.tv_water);
        tv_parking = (TextView) view.findViewById(R.id.tv_parking);

        tv_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                int imgResource = R.drawable.tickselected;
                i.setImageResource(imgResource);
                fieldInfo.water = 1;
            }

        });
        tv_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup container = (ViewGroup) view.getParent();
                ImageView i = (ImageView) container.getChildAt(1);
                int imgResource = R.drawable.tickselected;
                i.setImageResource(imgResource);
                fieldInfo.parking = 1;
            }

        });


        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:

                MapFragment fragment = new MapFragment();
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