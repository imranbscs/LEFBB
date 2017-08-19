package com.laeb.laebproject.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laeb.laebproject.R;
import com.laeb.laebproject.adapters.FootBallFieldsAdapter;
import com.laeb.laebproject.model.UpComingGames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariq on 8/17/2017.
 */

public class FragmentProfile extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.full_profile, container, false);
    }

}