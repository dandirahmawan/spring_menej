package com.menej.model.db;

import com.menej.keys.LabelModuleKeys;

import javax.persistence.*;

@Entity
@Table(name = "label_module")
@IdClass(LabelModuleKeys.class)
public class LabelModule {
    @Id
    @Column(name = "module_id")
    int moduleId;

    @Id
    @Column(name = "project_id")
    int projectId;

    @Id
    @Column(name = "label")
    String label;

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
