package com.laeb.laebproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.EditText;

import com.laeb.laebproject.adapters.FootBallFieldsAdapter;
import com.laeb.laebproject.adapters.UpComingGamesAdapter;
import com.laeb.laebproject.model.UpComingGames;

import java.util.ArrayList;
import java.util.List;

public class FootBallFieldActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<UpComingGames> listItems;
    EditText p_name, p_city, p_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_ball_field);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            listItems.add(new UpComingGames("RMD", "Riyadh", "Aug 16,2014 | 17:15"));
        }

        adapter = new FootBallFieldsAdapter(listItems, this);
        recyclerView.setAdapter(adapter);


    }
}
