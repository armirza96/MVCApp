package com.abdul.mirza.mvc.Model;

import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;
import com.abdul.mirza.mvc.Helper.SharedPreferencesHelper;

public class ListItem {
    PREFERENCE_NAME event;
    String time;
    String eventGivenName;

    public ListItem(PREFERENCE_NAME event, String time) {
        this.event = event;
        this.time = time;
    }

    public ListItem(String event, String time, SharedPreferencesHelper helper) {
        PREFERENCE_NAME e;

        switch(event) {
            case "EVENT_B":
                e = PREFERENCE_NAME.EVENT_B;
                eventGivenName = helper.getString(PREFERENCE_NAME.EVENT_B);
            break;
            case "EVENT_C":
                e = PREFERENCE_NAME.EVENT_C;
                eventGivenName = helper.getString(PREFERENCE_NAME.EVENT_C);
            break;
            default:
                e = PREFERENCE_NAME.EVENT_A;
                eventGivenName = helper.getString(PREFERENCE_NAME.EVENT_A);
                break;
        }

        this.event = e;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public PREFERENCE_NAME getEvent() {
        return event;
    }

    public String getEventGivenName() { return eventGivenName; }

    public int getEventNumber() {
        int number = 1;
        switch(event) {
            case EVENT_B:
                number = 2;
                break;
            case EVENT_C:
                number = 3;
                break;
        }

        return number;
    }
}
