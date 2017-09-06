package com.laeb.laebproject.fragments_booking;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Switch;

import com.laeb.laebproject.BookingActivity;
import com.laeb.laebproject.R;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by Imran on 9/3/2017.
 */

public class FragmentPaymentChoice extends Fragment implements OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_payment_details, container, false);
v.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        FragmentScratch fragment = new FragmentScratch();
        log.i("asd","scrtach");
        ((BookingActivity) getActivity()).addFragment(fragment);
    }
});
        return v;

    }

    @Override
    public void onClick(View v) {
        log.i("asd","scrtach out");
        switch (v.getId()) {
            case R.id.LayoutLAEB:
                FragmentScratch fragment = new FragmentScratch();
                log.i("asd","scrtach");
                ((BookingActivity) getActivity()).addFragment(fragment);
                break;

        }
    }
}
