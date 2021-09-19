package com.menej.model.db;

import com.menej.keys.LabelsKeys;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "labels")
@IdClass(LabelsKeys.class)
public class Labels {
    @Id
    @Column(name = "project_id")
    int projectId;

    @Id
    @Column(name = "label")
    String label;

    @Column(name = "color")
    String color;

    Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
