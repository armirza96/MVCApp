package com.abdul.mirza.mvc.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context)
    {
        sharedPreferences = context.getSharedPreferences("ProfilePreference",
                Context.MODE_PRIVATE );
    }

    public void saveString(PREFERENCE_NAME name, String value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name.toString(), value );
        editor.apply();
    }

    public void saveInt(PREFERENCE_NAME name, int value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name.toString(), value );
        editor.apply();
    }

    public String getString(PREFERENCE_NAME name)
    {
        return sharedPreferences.getString(name.toString(), null);
    }

    public int getInt(PREFERENCE_NAME name)
    {
        return sharedPreferences.getInt(name.toString(), -1);
    }
}