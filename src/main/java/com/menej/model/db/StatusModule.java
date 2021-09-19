package com.menej.model.db;

import javax.persistence.*;

@Entity
@Table(name = "status_module")
//@IdClass(StatusModuleKeys.class)
public class StatusModule {
    @Id
    @Column(name = "Id")
    int id;

    @Column(name = "project_id")
    int projectId;

    @Column(name = "status")
    String status;

    String color;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
