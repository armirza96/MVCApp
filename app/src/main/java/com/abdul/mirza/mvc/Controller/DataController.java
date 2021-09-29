package com.abdul.mirza.mvc.Controller;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.abdul.mirza.mvc.Helper.SharedPreferencesHelper;
import com.abdul.mirza.mvc.Model.DataModel;
import com.abdul.mirza.mvc.Model.ListItem;
import com.abdul.mirza.mvc.View.IDataView;

import java.util.List;

public class DataController implements IDataController{
    IDataView v;
    DataModel m;

    SharedPreferencesHelper helper;
    boolean showEventNames = true;

    public DataController(IDataView v, Context context) {
        helper = new SharedPreferencesHelper(context);

        this.v = v;
        this.m = new DataModel(helper);
    }

    @Override
    public void changeState() {
        showEventNames = !showEventNames;
        //v.updateUI(showEventNames);
        v.onRecyclerViewUpdate(m.getItems(), showEventNames);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSetup() {
        List<ListItem> items = m.getItems();
        String eventA, eventB, eventC;

        long eventACount =  items.stream().filter(e -> e.getEventNumber() == 1).count();
        long eventBCount =  items.stream().filter(e -> e.getEventNumber() == 2).count();
        long eventCCount =  items.stream().filter(e -> e.getEventNumber() == 3).count();

        if(showEventNames) {
            eventA = m.getEventA();
            eventB = m.getEventB();
            eventC = m.getEventC();
        } else {
            eventA = "Counter 1";
            eventB = "Counter 2";
            eventC = "Counter 3";
        }

        v.updateUI(items, showEventNames, eventA + ": " + eventACount, eventB + ": " + eventBCount, eventC + ": " + eventCCount, "Total Events: " + (eventACount + eventBCount + eventCCount));
    }


}
