package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.keys.DataProjectTeamKeys;

@Entity
@Table(name = "view_project_team")
@IdClass(DataProjectTeamKeys.class)
public class DataProjectTeam{
    @Id
    @Column(name = "project_id")
    int projectId;

    @Id
    @Column(name = "user_id")
    int userId;

    @Column(name = "user_name")
    String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}