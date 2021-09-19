package com.menej.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "view_invitation")
public class ViewInvitation {
    @Id
    @Column(name = "invitation_id")
    int invitationId;

    @Column(name = "invitation_code")
    String invitationCode;

    @Column(name = "user_id")
    int userId;

    @Column(name = "invitation_email")
    String invitationEmail;

    @Column(name = "status")
    String status;

    @Column(name = "invitation_date")
    Date invitationDate;

    @Column(name = "created_date")
    Date createdDate;

    @Column(name = "projectId")
    int projectId;

    @Column(name = "project_name")
    String projectName;

    @Column(name = "user_name_inviting")
    String userNameInviting;

    @Column(name = "pic_name")
    String picName;

    public int getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(int invitationId) {
        this.invitationId = invitationId;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInvitationEmail() {
        return invitationEmail;
    }

    public void setInvitationEmail(String invitationEmail) {
        this.invitationEmail = invitationEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(Date invitationDate) {
        this.invitationDate = invitationDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserNameInviting() {
        return userNameInviting;
    }

    public void setUserNameInviting(String userNameInviting) {
        this.userNameInviting = userNameInviting;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
