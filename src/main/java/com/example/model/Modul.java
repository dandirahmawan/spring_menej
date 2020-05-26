package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modul")
public class Modul {
	@Id
	@Column(name = "modul_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer modulId;
	
	@Column(name = "modul_name")
	private String modulName;
	
	@Column(name = "projectId", updatable = false)
	private Integer projectId;
	
	@Column(name = "user_id")
	private int userId;

	@Column(name = "created_date")
    private Date createdDate;
	
	@Column(name = "created_by", updatable = false)
	private int createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "modul_status")
	private String modulStatus;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_trash")
	private String isTrash;
	
	@Column(name = "percentage")
	private String percentage;
	
	// @ManyToOne(optional = false)
	// @JoinColumn(name = "user_id")
	// private User user;

	public Integer getModulId() {
		return modulId;
	}

	public void setModulId(Integer modulId) {
		this.modulId = modulId;
	}

	public String getModulName() {
		return modulName;
	}

	public void setModulName(String modulName) {
		this.modulName = modulName;
	}
 
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getModulStatus() {
		return modulStatus;
	}

	public void setModulStatus(String modulStatus) {
		this.modulStatus = modulStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsTrash() {
		return isTrash;
	}

	public void setIsTrash(String isTrash) {
		this.isTrash = isTrash;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	// public User getUser() {
	// 	return user;
	// }

	// public void setUser(User user) {
	// 	this.user = user;
	// }
	
}
