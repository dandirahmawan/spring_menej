package com.menej.keys;

import java.io.Serializable;

public class PermitionKeys implements Serializable{
	int projectId;
	int userId;
	int permitionCode;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getUserId() {
		return userId;
	} 
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPermitionCode() {
		return permitionCode;
	}
	public void setPermitionCode(int permitionCode) {
		this.permitionCode = permitionCode;
	}
	
	
}
