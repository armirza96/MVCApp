package com.abdul.mirza.mvc.Model;

import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;
import com.abdul.mirza.mvc.Helper.SharedPreferencesHelper;

public class SettingsModel {
    String eventA, eventB, eventC;
    int maxCount = 0;

    public SettingsModel(SharedPreferencesHelper helper) {
        eventA = helper.getString(PREFERENCE_NAME.EVENT_A);
        eventB = helper.getString(PREFERENCE_NAME.EVENT_B);
        eventC = helper.getString(PREFERENCE_NAME.EVENT_C);
        maxCount = helper.getInt(PREFERENCE_NAME.MAX_COUNT);
    }

    public String getEventA() {
        return eventA;
    }

    public String getEventB() {
        return eventB;
    }

    public String getEventC() {
        return eventC;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
