package com.menej.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "view_user_relation_member")
public class ViewUserRelationMember {

    @Id
    @Column(name = "user_id")
    int userId;

    @Column(name = "user_name")
    String userName;

    @Column(name = "email_user")
    String emailUser;

    @Column(name = "pic_profile")
    String picProfile;

    @Column(name = "pic_profile_detail")
    String picProfileDetail;

    @Column(name = "relate_date")
    Date relateDate;

    @Column(name = "project_id")
    int projectId;

    @Column(name = "is_related")
    String isRelated;

    @Column(name = "is_invited")
    String isInvited;

    @Column(name = "user_accessing")
    int userAccessing;

    @Column(name = "is_member")
    String isMember;

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

    public String getPicProfileDetail() {
        return picProfileDetail;
    }

    public void setPicProfileDetail(String picProfileDetail) {
        this.picProfileDetail = picProfileDetail;
    }

    public Date getRelateDate() {
        return relateDate;
    }

    public void setRelateDate(Date relateDate) {
        this.relateDate = relateDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getIsRelated() {
        return isRelated;
    }

    public void setIsRelated(String isRelated) {
        this.isRelated = isRelated;
    }

    public String getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(String isInvited) {
        this.isInvited = isInvited;
    }

    public int getUserAccessing() {
        return userAccessing;
    }

    public void setUserAccessing(int userAccessing) {
        this.userAccessing = userAccessing;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }
}
