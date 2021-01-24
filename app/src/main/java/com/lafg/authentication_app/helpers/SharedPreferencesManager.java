package com.lafg.authentication_app.helpers;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String APP_SETTINGS_FILE = "APP_SETTINGS";

    private SharedPreferencesManager() {
    }

    private static SharedPreferences getSharedPreferences() {
        return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    public static void setStringValue(String dataLabel, String dataValue) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(dataLabel, dataValue);
        editor.apply();
    }

    public static String getStringValue(String dataLabel) {
        return getSharedPreferences().getString(dataLabel, "");
    }

    public static void setLongValue(String dataLabel, long dataValue){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(dataLabel, dataValue);
        editor.apply();
    }

    public static long getLongValue(String dataLabel) {
        return getSharedPreferences().getLong(dataLabel, -1);
    }

    public static void removeDataValue(String dataLabel) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(dataLabel);
        editor.apply();
    }
}
