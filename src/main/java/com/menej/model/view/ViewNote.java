package com.menej.model.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_note")
public class ViewNote {
	@Id
	@Column(name = "note_id")
	private int noteId;
	
	@Column(name = "project_id")
	private int projectId;
	
	@Column(name = "module_id")
	private int moduleId;
	
	@Column(name = "bugs_id")
	private int bugsId;
	
	private String note;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "is_delete")
	private String isDelete;
	
	private String type;

	private String pic;
	
	@Column(name = "user_name")
	private String userName;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getBugsId() {
		return bugsId;
	}

	public void setBugsId(int bugsId) {
		this.bugsId = bugsId; 
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
