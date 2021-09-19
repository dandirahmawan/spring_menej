package com.menej.keys;

import java.io.Serializable;

public class BugsKeys  implements Serializable{
	private int modulId;
    private int projectId;
    private String note;

    public int getModulId() {
        return modulId;
    } 

    public void setModulId(int modulId) {
        this.modulId = modulId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
