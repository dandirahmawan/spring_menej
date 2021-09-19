package com.menej.model.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.menej.keys.ViewBugsKeys;

@Entity
@Table(name = "view_bugs")
@IdClass(ViewBugsKeys.class)
public class ViewBugs {
	@Id
	@Column(name = "bugs_id")
	private int bugsId;
	
	@Id
	@Column(name = "project_id")
	private int projectId;
	
	@Id
	@Column(name = "modul_id")
	private int modulId;
	
	@Id
	private String note;
	
	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "modul_name")
	private String modulName;
	
	@Column(name = "bug_status")
	private String bugStatus;
	
	@Column(name = "is_delete")
	private String isDelete;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "count_note")
	private int countNote;

	@Column(name = "created_by_name")
	String createdByName;
	
	private int pic;
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public int getModulId() {
		return modulId;
	}

	public void setModulId(int modulId) {
		this.modulId = modulId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getModulName() {
		return modulName;
	}

	public void setModulName(String modulName) {
		this.modulName = modulName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getBugStatus() {
		return bugStatus;
	}

	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPic() {
		return pic;
	}

	public void setPic(int pic) {
		this.pic = pic;
	}

	public int getBugsId() {
		return bugsId;
	}

	public void setBugsId(int bugsId) {
		this.bugsId = bugsId;
	}

	public int getCountNote() {
		return countNote;
	}

	public void setCountNote(int countNote) {
		this.countNote = countNote;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
}
