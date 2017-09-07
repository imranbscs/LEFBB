package com.laeb.laebproject.fragments_booking;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laeb.laebproject.BookingActivity;
import com.laeb.laebproject.R;

/**
 * Created by Imran on 9/5/2017.
 */

public class FragmentLocationDetails extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_location_details, container, false);
        Button b = (Button) v.findViewById(R.id.btnPayNow);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentPaymentChoice fragment = new FragmentPaymentChoice();
                Log.i("asd", "FragmentPaymentChoice");
                ((BookingActivity) getActivity()).addFragment(fragment);
            }
        });
        return v;

    }

}
