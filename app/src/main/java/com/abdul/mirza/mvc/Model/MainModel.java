package com.abdul.mirza.mvc.Model;

import android.content.Context;

import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;
import com.abdul.mirza.mvc.Helper.SharedPreferencesHelper;

public class MainModel {

    int MAX_COUNT;
    int itemsCount;

    public MainModel(SharedPreferencesHelper helper) {
        MAX_COUNT = helper.getInt(PREFERENCE_NAME.MAX_COUNT);
    }

    public int getTotalCount() {
        return MAX_COUNT;
    }

    public int increaseCount() { return itemsCount++; }
    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public boolean canAddMoreItems() { return MAX_COUNT - itemsCount > 0; }
}
