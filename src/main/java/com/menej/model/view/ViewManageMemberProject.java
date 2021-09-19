package com.menej.model.view;

import com.menej.keys.ViewManageMemberProjectKeys;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "view_manage_member_project")
@IdClass(ViewManageMemberProjectKeys.class)
public class ViewManageMemberProject {
    @Id
    @Column(name="user_id")
    private int userId;

    @Id
    @Column(name = "project_id")
    int projectId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_user")
    private String emailUser;

    @Column(name = "pic_profile")
    private String picProfile;

    @Column(name = "pic_profile_detail")
    private String picProfileDetail;

    @Column(name = "user_accessing")
    int userAccessing;

    @Column(name = "is_member")
    String isMember;

    @Column(name = "is_related")
    String isRelated;

    @Column(name = "pic")
    int pic;

    @Column(name = "relate_date")
    Date relateDate;

    @Column(name = "is_invited")
    String isInvited;

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

    public int getUserAccessing() {
        return userAccessing;
    }

    public void setUserAccessing(int userAccessing) {
        this.userAccessing = userAccessing;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getIsRelated() {
        return isRelated;
    }

    public void setIsRelated(String isRelated) {
        this.isRelated = isRelated;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public Date getRelateDate() {
        return relateDate;
    }

    public void setRelateDate(Date relateDate) {
        this.relateDate = relateDate;
    }

    public String getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(String isInvited) {
        this.isInvited = isInvited;
    }
}
