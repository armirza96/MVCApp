package com.abdul.mirza.mvc.Controller;

import android.widget.EditText;

import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;

import java.util.Map;

public interface ISettingsController {
    boolean onSetup();
    boolean isEditing();
    void changeState();
    void saveData(Map<EditText, PREFERENCE_NAME> map, EditText maxCount);
}
