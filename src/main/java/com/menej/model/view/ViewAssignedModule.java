package com.menej.model.view;

import com.menej.keys.AssignedModuleKey;

import javax.persistence.*;

@Entity
@Table(name = "view_assigned_module")
@IdClass(AssignedModuleKey.class)
public class ViewAssignedModule {
    @Id
    @Column(name = "user_id")
    int userId;

    @Id
    @Column(name = "module_id")
    int moduleId;

    @Column(name = "project_id")
    int projectId;

    @Column(name = "user_name")
    String userName;

    @Column(name = "email_user")
    String emailUser;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
}
