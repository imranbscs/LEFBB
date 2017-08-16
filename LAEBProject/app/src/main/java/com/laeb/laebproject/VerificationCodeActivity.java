package com.laeb.laebproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VerificationCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        getSupportActionBar().hide();
    }

    public void submitClicked(View view) {
        startActivity(new Intent(this, MainMenuActivity.class));
    }
}
