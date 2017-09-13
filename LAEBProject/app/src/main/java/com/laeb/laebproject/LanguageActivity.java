package com.laeb.laebproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.laeb.laebproject.general.Prefs;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        getSupportActionBar().hide();
    }

    public void englishClicked(View view) {
        //startActivity(new Intent(this, AddNumberActivity.class));
        showActivity();
    }

    public void showActivity(){
        if(Prefs.getString(this, Prefs.auth_key).length()>10){
            startActivity(new Intent(this, HomeActivity.class));
        }else{
            startActivity(new Intent(this, AddNumberActivity.class));
        }
    }
}
