package com.menej.model;

import com.menej.model.db.UserPrivacyTab;
import com.menej.model.view.ViewTab;
import org.json.simple.JSONObject;

import java.util.List;

public class TabPage {
    ViewTab tab;
    String columnTab;
    List<UserPrivacyTab> userPrivacyTab;
    String rowTab;

    public String getColumnTab() {
        return columnTab;
    }

    public void setColumnTab(String columnTab) {
        this.columnTab = columnTab;
    }

    public ViewTab getTab() {
        return tab;
    }

    public void setTab(ViewTab tab) {
        this.tab = tab;
    }

    public List<UserPrivacyTab> getUserPrivacyTab() {
        return userPrivacyTab;
    }

    public void setUserPrivacyTab(List<UserPrivacyTab> userPrivacyTab) {
        this.userPrivacyTab = userPrivacyTab;
    }

    public String getRowTab() {
        return rowTab;
    }

    public void setRowTab(String rowTab) {
        this.rowTab = rowTab;
    }
}
