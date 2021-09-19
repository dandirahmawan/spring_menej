package com.menej.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bugs")
//@IdClass(BugsKeys.class)
public class Bugs {
	@Id
	@Column(name = "bugs_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bugsId;
	
//	@Id
	@Column(name = "modul_id")
	private int modulId;
	
//	@Id 
	@Column(name = "project_id")
	private int projectId;
	
//	@Id
	private String note;

	@Column(name = "bug_status")
	String bugStatus;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "is_delete")
	private String isDelete;

	@Column(name = "created_by")
	int createdBy;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public int getBugsId() {
		return bugsId;
	}

	public void setBugsId(int bugsId) {
		this.bugsId = bugsId;
	}

	public String getBugStatus() {
		return bugStatus;
	}

	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
}
