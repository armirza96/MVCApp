package com.abdul.mirza.mvc.View;

public interface ISettingsView {

//    void setEventAETText(String text);
//    void setEventBETText(String text);
//    void setEventCETText(String text);
//    void setMaxCountETText(String text);

    void updateUIState(boolean isEditing);
    void setEditTextText(String eventAText, String eventBText, String eventCText, String itemsCount);

    void showToast(String text);
}
