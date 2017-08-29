package com.laeb.laebproject.general;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Prefs {
    public static final String auth_key="auth_key";
    public static final String ACTIVE_ID = "ACTIVE_ID";
    public static final  String FAMILY_ID = "FAMILY_ID";

    public static void putString(Context ctx, String key, String value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getString(Context ctx, String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString(key,"");
    }

    public static void putBool(Context ctx, String key, boolean value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();

    }
    public static boolean getBool(Context ctx, String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getBoolean(key,false);
    }

}
