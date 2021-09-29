package com.abdul.mirza.mvc.View;

import com.abdul.mirza.mvc.Model.ListItem;

import java.util.List;

public interface IDataView {
    void updateUI(List<ListItem> items, boolean showEventNames, String eventA, String eventB, String eventC, String totalEvents);
    void onRecyclerViewUpdate(List<ListItem> items, boolean showEventNames);
}
