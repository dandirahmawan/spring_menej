package com.menej.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_tab")
public class ViewTab {
    @Id
    @Column(name = "tab_id")
    private int tabId;

    @Column(name = "tab_name")
    private String tabName;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "createdBy")
    private int createdBy;

    @Column(name = "file_frm")
    private String fileFrm;

    @Column(name = "privacy")
    private String privacy;

    @Column(name = "is_delete")
    private String isDelete;

    @Column(name = "type")
    private String type;

    @Column(name = "source")
    private String source;

    @Column(name = "replaced_tab_id")
    private int replacedTabId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "pic")
    private String pic;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_user")
    private String emailUser;

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getReplacedTabId() {
        return replacedTabId;
    }

    public void setReplacedTabId(int replacedTabId) {
        this.replacedTabId = replacedTabId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
