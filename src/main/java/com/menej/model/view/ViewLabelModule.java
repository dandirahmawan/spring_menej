package com.menej.model.view;

import com.menej.keys.LabelModuleKeys;
import com.menej.model.db.LabelModule;

import javax.persistence.*;

@Entity
@Table(name = "view_label_module")
@IdClass(LabelModuleKeys.class)
public class ViewLabelModule {
    @Id
    @Column(name = "module_id")
    int moduleId;

    @Id
    @Column(name = "project_id")
    int projectId;

    @Id
    @Column(name = "label")
    String label;

    @Column(name = "color")
    String color;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
