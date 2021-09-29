package com.abdul.mirza.mvc.Controller;

import android.content.Context;
import android.widget.EditText;

import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;
import com.abdul.mirza.mvc.Helper.SharedPreferencesHelper;
import com.abdul.mirza.mvc.Model.SettingsModel;
import com.abdul.mirza.mvc.View.ISettingsView;

import java.util.Map;


public class SettingsController implements ISettingsController {
    ISettingsView v;
    SettingsModel m;

    SharedPreferencesHelper helper;

    boolean isEditing = false;

    public SettingsController(ISettingsView v, Context context) {
        helper = new SharedPreferencesHelper(context);

        this.v = v;
        this.m = new SettingsModel(helper);
    }

    @Override
    public boolean onSetup() {
        boolean isDataValid = m.getEventA() != null && m.getEventB() != null && m.getEventC() != null && m.getMaxCount() > 0;
        if(isDataValid) {
            v.setEditTextText(m.getEventA(), m.getEventB(), m.getEventC(), Integer.toString(m.getMaxCount()));
            return true;
        }
        return false;
    }

    @Override
    public boolean isEditing() {
        return isEditing;
    }

    @Override
    public void changeState() {
        isEditing = !isEditing;
        v.updateUIState(isEditing);
    }

    @Override
    public void saveData(Map<EditText, PREFERENCE_NAME> map, EditText maxCount) {
        boolean isAllDataValid = false;
        boolean isPreviousDataValid = false;

        for(Map.Entry<EditText, PREFERENCE_NAME> entry : map.entrySet()) {
            String val = entry.getKey().getText().toString();

            if(isValid(val)) {
                isPreviousDataValid = true;
            } else {
                isPreviousDataValid = false;
                break;
            }
        }

        if(isPreviousDataValid) {
            int maxCountVal = Integer.parseInt(maxCount.getText().toString());
            if(maxCountVal >= 5 && maxCountVal <= 200) {
                isAllDataValid = true;
            }

            if(isAllDataValid) {
                for(Map.Entry<EditText, PREFERENCE_NAME> entry : map.entrySet()) {
                    String val = entry.getKey().getText().toString();

                    helper.saveString(entry.getValue(), val);
                }

                helper.saveInt(PREFERENCE_NAME.MAX_COUNT, maxCountVal);

                v.showToast("Data Saved!");
                changeState();
            } else {
                v.showToast("Data not Saved, problems in data!");
            }
        } else {
            v.showToast("Data not Saved, problems in data!");
        }
    }


    boolean isValid(String val) {
        return !val.isEmpty() && val.trim().length() > 0;
    }
}
