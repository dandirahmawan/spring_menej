package com.menej.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.menej.keys.ProjectTeamKeys;

@Entity
@Table(name = "project_team")
@IdClass(ProjectTeamKeys.class)
public class ProjectTeam {
	
	@Id
	@Column(name = "project_id")
	private int projectId;
	
	@Id
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "user_status")
	private String userStatus;
	
	@Column(name = "created_date")
	private Date createdDate;

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

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
