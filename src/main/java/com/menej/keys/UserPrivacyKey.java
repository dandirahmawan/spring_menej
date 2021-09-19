package com.menej.keys;

import java.io.Serializable;

public class UserPrivacyKey implements Serializable {
    int userId;
    int tabId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }
}
