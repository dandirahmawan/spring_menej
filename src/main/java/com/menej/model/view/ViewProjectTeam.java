package com.menej.model.view;

import com.menej.keys.ProjectTeamKeys;

import javax.persistence.*;

@Entity
@Table(name = "view_project_team")
@IdClass(ProjectTeamKeys.class)
public class ViewProjectTeam {
    @Id
    @Column(name = "project_id")
    int projectId;

    @Id
    @Column(name = "user_id")
    int userId;

    @Column(name = "user_name")
    String userName;

    @Column(name = "email_user")
    String emailUser;

    @Column(name = "pic_profile")
    String picProfile;

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

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPicProfile() {
        return picProfile;
    }

    public void setPicProfile(String picProfile) {
        this.picProfile = picProfile;
    }
}
