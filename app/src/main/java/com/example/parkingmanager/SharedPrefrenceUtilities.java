package com.example.parkingmanager;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceUtilities {

    //SharedPreference Key
    public static final String spIsLoggedin = "isLoggedin";
    public static final String spFirstName ="name";
    public static final String spEmail = "email";
    public static final String spUserId = "userId";
    public static final String spMobileNo = "mobileNo";

    //Store string using Shared Preference
    public static void setSPstring(Context mContext, String key, String value) {
        SharedPreferences pref =mContext.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //Store boolean using Shared Preference
    public static void setSPboolean(Context mContext,String key, boolean value) {
        SharedPreferences pref =mContext.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    //Store integer using Shared Preference
    public static void setSPineger(Context mContext,String key, int value) {
        SharedPreferences pref =mContext.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //get string value from Shared Preference
    public static String getSPstringValue(Context mContext,String key)
    {
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key,null);
        return value;
    }

    //get int value from Shared Preference
    public static int getSPintValue(Context mContext,String key)
    {
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("MyPref",Context.MODE_PRIVATE);

    }

}
