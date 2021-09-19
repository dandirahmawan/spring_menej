package com.menej.keys;

import java.io.Serializable;

public class TabKeys implements Serializable {
    int tabId;
    int projectId;

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
