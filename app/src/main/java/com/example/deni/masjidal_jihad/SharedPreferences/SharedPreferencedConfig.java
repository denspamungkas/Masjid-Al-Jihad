package com.example.deni.masjidal_jihad.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencedConfig {

    public static final String PREFERENCE_ALJIHAD_APP = "prefAljihadApp";

    public static final String PREFERENCE_USERNAME = "prefUsername";
    public static final String PREFERENCE_LEVEL_USER = "prefLevelUser";
    public static final String PREFERENCE_KEY_USER = "prefKeyUser";
    public static final String PREFERENCE_IS_LOGIN = "prefIsLogin";

    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;

    public SharedPreferencedConfig(Context context) {

        preferences = context.getSharedPreferences(PREFERENCE_ALJIHAD_APP, Context.MODE_PRIVATE);
        preferencesEditor = preferences.edit();
    }

    public void savePrefString(String keyPref, String value){
        preferencesEditor.putString(keyPref, value);
        preferencesEditor.commit();
    }

    public void savePrefInt(String keyPref, int value){
        preferencesEditor.putInt(keyPref, value);
        preferencesEditor.commit();
    }

    public void savePrefBoolean(String keyPref, boolean value){
        preferencesEditor.putBoolean(keyPref, value);
        preferencesEditor.commit();
    }


    public String getPreferenceUsername(){
        return preferences.getString(PREFERENCE_USERNAME, "");
    }

    public String getPreferenceLevelUser(){
        return preferences.getString(PREFERENCE_LEVEL_USER, "");
    }

    public String getPreferenceKeyUser(){
        return preferences.getString(PREFERENCE_KEY_USER, "");
    }

    public Boolean getPreferenceIsLogin(){
        return preferences.getBoolean(PREFERENCE_IS_LOGIN, false);
    }
}
