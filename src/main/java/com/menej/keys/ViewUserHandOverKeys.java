package com.menej.keys;

import java.io.Serializable;

public class ViewUserHandOverKeys implements Serializable {
    int projectId;
    int userId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
