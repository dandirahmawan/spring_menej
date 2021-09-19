package com.menej.model;
import java.util.Date;

public class ViewUserRelationMemberData {
    int userId;
    String userName;
    String emailUser;
    String picProfile;
    String picProfileDetail;
    Date relateDate;
    int projectId;
    String isRelated;
    String isInvited;
    int userAccessing;
    String isMember;

    public ViewUserRelationMemberData(int userId, String userName,
                                      String picProfile, String picProfileDetail,
                                      Date relateDate, int projectId,
                                      String isRelated, String isInvited,
                                      int userAccessing, String isMember){

        this.userId = userId;
        this.userName = userName;
        this.picProfile = picProfile;
        this.picProfileDetail = picProfileDetail;
        this.relateDate = relateDate;
        this.projectId = projectId;
        this.isRelated = isRelated;
        this.isInvited = isInvited;
        this.userAccessing = userAccessing;
        this.isMember = isMember;
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
