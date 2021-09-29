package com.abdul.mirza.mvc.Controller;

import android.content.Context;

import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;
import com.abdul.mirza.mvc.Helper.SharedPreferencesHelper;
import com.abdul.mirza.mvc.Model.ListItem;
import com.abdul.mirza.mvc.Model.MainModel;
import com.abdul.mirza.mvc.View.IMainView;

public class MainController implements IMainController {
    IMainView v;
    MainModel m;

    SharedPreferencesHelper helper;

    public MainController(IMainView mv, Context context) {
        helper = new SharedPreferencesHelper(context);

        this.v = mv;
        this.m = new MainModel(helper);
    }

    @Override
    public boolean onSetup() {
        String eventA = helper.getString(PREFERENCE_NAME.EVENT_A);
        String eventB = helper.getString(PREFERENCE_NAME.EVENT_B);
        String eventC = helper.getString(PREFERENCE_NAME.EVENT_C);

        boolean areAllNamed = eventA != null && eventB != null && eventC != null;
        if(areAllNamed) {

            String savedData = helper.getString(PREFERENCE_NAME.LIST);

            if(savedData != null) {
                String[] items = savedData.split(", ");
                m.setItemsCount(items.length);
            } else {
                m.setItemsCount(0);
            }

            v.updateUI(eventA, eventB, eventC,  m.getItemsCount(), m.getTotalCount());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onSaveDataToSharedPreferences(ListItem item) {
        if(m.canAddMoreItems()) {
            String moreDataToSave = "";

            moreDataToSave += item.getEvent() + " " + item.getTime() + ", ";

            moreDataToSave = moreDataToSave.substring(0, moreDataToSave.length() - 2);

            String savedData = helper.getString(PREFERENCE_NAME.LIST);

            if(savedData != null) {
                savedData += ", " + moreDataToSave;
            }
            else {
                savedData = moreDataToSave;
            }

            m.increaseCount();
            helper.saveString(PREFERENCE_NAME.LIST, savedData);

            v.setTextViewText(Integer.toString(m.getItemsCount()));
            v.setProgressValue(m.getItemsCount());
        }
    }
}
