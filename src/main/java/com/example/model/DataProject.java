package com.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataProject {
	private int projectId;
	private String projectName;
	private String pic;
	private int createdBy;
	private String createdDate;
	private String isDelete;
	private String isClose;
	private String userName;
	private String picName;
	private Long countBugs;
	private Long countTeam;
	private Long countModule;
	
	public DataProject(int projectId, String pic, String projectName, 
						int createdBy, Date createdDate, 
						String isDelete, String isClose,
						String userName, Long countTeam, String picName, Long countBugs, Long countModule) {
		
		this.projectId = projectId; 
		this.projectName = projectName;
		this.pic = pic;
		this.createdBy = createdBy;
		this.createdDate = stringDate(createdDate);
		this.isDelete = isDelete;
		this.isClose = isClose;
		this.userName = userName;
		this.picName = picName;
		this.countBugs = countBugs;
		this.countTeam = countTeam;
		this.countModule = countModule;
	}
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getIsClose() {
		return isClose;
	}
	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	
	public Long getCountBugs() {
		return countBugs;
	}

	public void setCountBugs(Long countBugs) {
		this.countBugs = countBugs;
	}
	
	public Long getCountTeam() {
		return countTeam;
	}

	public void setCountTeam(Long countTeam) {
		this.countTeam = countTeam;
	}
	
	public Long getCountModule() {
		return countModule;
	}

	public void setCountModule(Long countModule) {
		this.countModule = countModule;
	}

	public String stringDate(Date date) {
		String pattern = "dd MMMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String d = simpleDateFormat.format(date);
		return d;
	}
	
}
