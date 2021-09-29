package com.abdul.mirza.mvc.Controller;

import com.abdul.mirza.mvc.Model.ListItem;

public interface IMainController {
    boolean onSetup();

    void onSaveDataToSharedPreferences(ListItem item);
}
