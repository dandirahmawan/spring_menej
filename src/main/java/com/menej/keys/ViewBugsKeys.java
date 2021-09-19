package com.menej.keys;

import java.io.Serializable;

public class ViewBugsKeys implements Serializable{
	private static final long serialVersionUID = 1L;
	int modulId;
	int projectId;
	String note;
	int bugsId;
	
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
	public int getBugsId() {
		return bugsId;
	}
	public void setBugsId(int bugsId) {
		this.bugsId = bugsId;
	}
	
	
}
