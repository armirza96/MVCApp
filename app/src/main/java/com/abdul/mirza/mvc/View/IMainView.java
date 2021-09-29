package com.abdul.mirza.mvc.View;

public interface IMainView {
    void setTextViewText(String text);
    void setProgressValue(int itemsCount);
    void updateUI(String btnEventAText, String btnEventBText, String btnEventCText, int itemsCount, int maxCount);
}
