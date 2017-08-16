package com.laeb.laebproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);
        getSupportActionBar().hide();
    }

    public void continueClicked(View view) {
        startActivity(new Intent(this, VerificationCodeActivity.class));
    }
}
