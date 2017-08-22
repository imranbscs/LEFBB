package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;


/**
 * Created by tariq on 8/19/2017.
 */

public class FragmentCreateField extends Fragment implements View.OnClickListener {

    String Type;
    EditText ed_name;
    EditText ed_city;
    EditText ed_size;
    TextView grass_pitch;
    TextView clay_pitch;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_field, container, false);
        View b = (View) view.findViewById(R.id.nextBtn);
        ed_name = (EditText) view.findViewById(R.id.ed__name);
        ed_city = (EditText) view.findViewById(R.id.ed_city);
        ed_size = (EditText) view.findViewById(R.id.field_size);
        grass_pitch = (TextView) view.findViewById(R.id.grass_pitch);
        clay_pitch = (TextView) view.findViewById(R.id.clay_pitch);


        b.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
               final FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.name = ed_name.getText().toString();
                fieldInfo.city = ed_city.getText().toString();
                fieldInfo.size = ed_size.getText().toString();

                 grass_pitch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int imgResource = R.drawable.tickselected;
                        grass_pitch.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                        imgResource = R.drawable.tick;
                        clay_pitch.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                        fieldInfo.type  = "Grass";
                    }
                });
                clay_pitch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int imgResource = R.drawable.tickselected;
                        clay_pitch.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                        imgResource = R.drawable.tick;
                        grass_pitch.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                        fieldInfo.type = "Clay";
                    }
                });

                FieldFacilities fragment = new FieldFacilities();
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
