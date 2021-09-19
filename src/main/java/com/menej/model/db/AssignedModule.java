package com.menej.model.db;

import com.menej.keys.AssignedModuleKey;

import javax.persistence.*;

@Entity
@Table(name = "assigned_module")
@IdClass(AssignedModuleKey.class)
public class AssignedModule {

    @Id
    @Column(name = "user_id")
    int userId;

    @Id
    @Column(name = "module_id")
    int moduleId;

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
}
