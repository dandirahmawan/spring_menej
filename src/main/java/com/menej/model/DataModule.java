package com.menej.model;

import com.menej.model.db.LabelModule;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class DataModule {
    private int projectId;
    private int modulId;
    private String modulName;
//    private String userName;
//    private int userId;
    private String pic;
//    private String emailUser;
    private Date createdDate;
    private int createdBy;
    private String updatedBy;
    private String updatedDate;
    private Date endDate;
    private String modulStatus;
    private String description;
    private String isTrash;
    private String percentage;
    private Long countBugs;
    private Long countBugsClose;
    private Long countDoc;
    private Long isMember;
    private Long countNote;
    private String projectName;
    private Boolean isSelected = false;

    public DataModule(int projectId,
    					String projectName,
    					int modulId, 
    					String modulName, 
//    					int userId,
    					String pic,
    					String userName, 
    					String emailUser,  
    					Date createdDate, 
    					int createdBy, 
    					String updatedBy, 
    					String updatedDate, 
    					Date endDate, 
    					String modulStatus, 
    					String description, 
    					String isTrash,
    					String percentage,
    					Long countNote,
    					Long countBugs,
    					Long countBugsClose,
    					Long countDoc, 
    					Long isMember){
    	
        this.projectId      = projectId;
        this.modulId        = modulId;
        this.modulName      = modulName;
//        this.userId         = userId;
        this.pic			= pic;
//        this.emailUser      = emailUser;
        this.createdDate    = createdDate;
        this.createdBy      = createdBy;
        this.updatedBy      = updatedBy;
        this.updatedDate    = updatedDate;
        this.endDate        = endDate;
        this.modulStatus    = modulStatus;
        this.description    = description;
        this.isTrash        = isTrash;
        this.percentage     = percentage;
        this.countBugs      = countBugs;
        this.countBugsClose = countBugsClose;
        this.countDoc       = countDoc;
//        this.userName       = userName;
        this.isMember 		= isMember;
        this.projectName 	= projectName;
        this.countNote 		= countNote;
    }

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

    public String getModulName() {
        return modulName;
    }

    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

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

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
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

    public Long getCountBugs() {
        return countBugs;
    }

    public void setCountBugs(Long countBugs) {
        this.countBugs = countBugs;
    }

    public Long getCountDoc() {
        return countDoc;
    }

    public void setCountDoc(Long countDoc) {
        this.countDoc = countDoc;
    }

    public String stringDate(Date date) {
		String pattern = "dd MMMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String d = simpleDateFormat.format(date);
		return d;
	}

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getEmailUser() {
//        return emailUser;
//    }
//
//    public void setEmailUser(String emailUser) {
//        this.emailUser = emailUser;
//    }

	public Long getIsMember() {
		return isMember;
	}

	public void setIsMember(Long isMember) {
		this.isMember = isMember;
	}

	public String getPic() {
		return pic;
	}

	public void setIsMember(String pic) {
		this.pic = pic;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Long getCountNote() {
		return countNote;
	}

	public void setCountNote(Long countNote) {
		this.countNote = countNote;
	}
	
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public Boolean getIsSelected() {
		return isSelected;
	}

    public Long getCountBugsClose() {
        return countBugsClose;
    }

    public void setCountBugsClose(Long countBugsClose) {
        this.countBugsClose = countBugsClose;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}