package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_data_module")
public class ViewModule {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "modul_id")
    private int modulId;

    @Column(name = "modul_name")
    private String modulName;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "updated_by")
    private int updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "modul_status")
    private String modulStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "is_trash")
    private String isTrash;

    @Column(name = "percentage")
    private String percentage;

    @Column(name = "count_bugs")
    private int countBugs;

    @Column(name = "count_doc_file")
    private int countDoc;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getModulId() {
        return modulId;
    }

    public void setModulId(int modulId) {
        this.modulId = modulId;
    }

    public String getModulName() {
        return modulName;
    }

    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getModulStatus() {
        return modulStatus;
    }

    public void setModulStatus(String modulStatus) {
        this.modulStatus = modulStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsTrash() {
        return isTrash;
    }

    public void setIsTrash(String isTrash) {
        this.isTrash = isTrash;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public int getCountBugs() {
        return countBugs;
    }

    public void setCountBugs(int countBugs) {
        this.countBugs = countBugs;
    }

    public int getCountDoc() {
        return countDoc;
    }

    public void setCountDoc(int countDoc) {
        this.countDoc = countDoc;
    }

    
}