package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.keys.BugsKeys;

@Entity
@Table(name = "bugs")
@IdClass(BugsKeys.class)
public class Bugs {
	@Id
	@Column(name = "modul_id")
	private int modulId;
	
	@Id
	@Column(name = "project_id")
	private int projectId;
	
	@Id
	private String note;

	@Column(name = "create_date")
	private Date createDate;

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

}
