package com.menej.model.db;

import com.menej.keys.AssignedModuleKey;
import com.menej.model.view.ViewModule;

import javax.persistence.*;

@Entity
@Table(name = "view_assigned_module")
@IdClass(AssignedModuleKey.class)
public class AssignedModuleReference {

    @Id
    @Column(name = "user_id")
    int userId;

    @Id
    @Column(name = "module_id", insertable = false, updatable = false)
    int moduleId;

    @Column(name = "user_name")
    String userName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id", referencedColumnName = "modul_id")
    ViewModule viewModule;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
