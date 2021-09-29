package com.abdul.mirza.mvc.Model;

import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;
import com.abdul.mirza.mvc.Helper.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    List<ListItem> items;
    String eventA, eventB, eventC;

    public DataModel(SharedPreferencesHelper helper) {
        this.items = getItemsFromSharedPreferences(helper);
        eventA = helper.getString(PREFERENCE_NAME.EVENT_A);
        eventB = helper.getString(PREFERENCE_NAME.EVENT_B);
        eventC = helper.getString(PREFERENCE_NAME.EVENT_C);
    }

    private List<ListItem> getItemsFromSharedPreferences(SharedPreferencesHelper helper) {
        String savedData = helper.getString(PREFERENCE_NAME.LIST);

        List<ListItem> items = new ArrayList<ListItem>();
        String[] data = savedData.split(", ");

        for(String d: data) {
            String[] components = d.split(" ");
            String event = components[0];

            items.add(new ListItem(event, components[1], helper));
        }

        return items;
    }

    public List<ListItem> getItems () {return items; }

    public String getEventA() {
        return eventA;
    }

    public String getEventB() {
        return eventB;
    }

    public String getEventC() {
        return eventC;
    }
}
