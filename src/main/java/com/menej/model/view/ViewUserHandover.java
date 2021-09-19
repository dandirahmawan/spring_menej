package com.menej.model.view;

import com.menej.keys.ViewUserHandOverKeys;

import javax.persistence.*;

@Entity
@Table(name = "view_user_handover")
@IdClass(ViewUserHandOverKeys.class)
public class ViewUserHandover {
    @Id
    @Column(name = "project_id")
    int projectId;

    @Id
    @Column(name = "user_id")
    int userId;

    @Column(name = "user_status")
    String userStatus;

    @Column(name = "user_name")
    String userName;

    @Column(name = "email_user")
    String emailUser;

    @Column(name = "has_module")
    String hasModule;

    @Column(name = "pic_profile")
    String picProfile;

    @Column(name = "is_member")
    String isMember;

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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHasModule() {
        return hasModule;
    }

    public void setHasModule(String hasModule) {
        this.hasModule = hasModule;
    }

    public String getPicProfile() {
        return picProfile;
    }

    public void setPicProfile(String picProfile) {
        this.picProfile = picProfile;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }
}
