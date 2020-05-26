package com.example.model;

import com.example.keys.TabKeys;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tab")
public class Tab {
    @Id
    @Column(name = "tab_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tabId;

    @Column(name = "project_id")
    int projectId;

    @Column(name = "tab_name")
    String tabName;

    @Column(name = "created_date")
    Date createdDate;

    @Column(name = "created_by")
    int createdBy;

    @Column(name = "file_frm")
    String fileFrm;

    @Column(name = "privacy")
    String privacy;

    @Column(name = "is_delete")
    String isDelete;

    @Column(name = "type")
    String type;

    @Column(name = "source")
    Integer source;

    @Column(name = "replaced_tab_id")
    Integer replacedTabId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public void setIsDelete(String isDelte) {
        this.isDelete = isDelte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = (source == null) ? 0 : source;
    }

    public Integer getReplacedTabId() {
        return replacedTabId;
    }

    public void setReplacedTabId(Integer replacedTabId) {
        this.replacedTabId = replacedTabId;
    }
}
