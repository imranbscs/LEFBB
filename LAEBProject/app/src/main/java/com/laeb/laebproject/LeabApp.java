package com.laeb.laebproject;

import android.app.Application;

/**
 * Created by tariq on 9/9/2017.
 */

public class LeabApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/avenir-roman.ttf");
    }
}