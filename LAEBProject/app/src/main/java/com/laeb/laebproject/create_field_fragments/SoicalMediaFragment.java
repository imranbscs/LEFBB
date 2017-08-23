package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;

/**
 * Created by tariq on 8/21/2017.
 */

public class SoicalMediaFragment extends Fragment implements View.OnClickListener {

    FieldInfo fieldInfo;
    CustomBinder oCustom;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_socail_media, container, false);
        View b = (View) view.findViewById(R.id.nextBtn);
        oCustom = (CustomBinder) getArguments().get("complexObject");
        fieldInfo = oCustom.getField();
        b.setOnClickListener(this);
        return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                WeeklyScheduleFragment fragment = new WeeklyScheduleFragment();
                Bundle args = new Bundle();
                CustomBinder oCustom = new CustomBinder();
                oCustom.setList(fieldInfo);
                args.putSerializable("complexObject", oCustom);
                fragment.setArguments(args);
                ((CreateFieldActivity)getActivity()).addFragment(fragment);
                break;
        }
    }
}
