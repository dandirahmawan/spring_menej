package com.example.model;

public class DataTab {
    Integer tabId;
    Integer projectId;
    String tabName;
    Integer createdBy;
    String fileFrm;
    String privacy;
    String isDelete;
    String userName;
    Integer userId;

    public DataTab(Integer tabId, Integer projectId, String tabName, Integer createdBy, String fileFrm, String privacy, String isDelete, String userName, Integer userId){

        this.tabId      = tabId;
        this.projectId  = projectId;
        this.tabName    = tabName;
        this.createdBy  = createdBy;
        this.fileFrm    = fileFrm;
        this.privacy    = privacy;
        this.isDelete   = isDelete;
        this.userName   = userName;
        this.userId     = userId;

    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getFileFrm() {
        return fileFrm;
    }

    public void setFileFrm(String fileFrm) {
        this.fileFrm = fileFrm;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
