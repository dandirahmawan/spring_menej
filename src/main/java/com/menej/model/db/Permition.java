package com.menej.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.menej.keys.PermitionKeys;

@Entity
@Table(name = "permition")
@IdClass(PermitionKeys.class)
public class Permition {
	@Id
	@Column(name = "project_id")
	private int projectId;

	@Id
	@Column(name = "user_id")
	private int userId;
	
	@Id
	@Column(name = "permition_code")
	private int permitionCode;

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
