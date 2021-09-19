package com.menej.model.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.menej.keys.DocumentFileKeys;

@Entity
@Table(name = "view_document_file")
@IdClass(DocumentFileKeys.class)
public class ViewDocumentFile {

	@Id
	@Column(name = "id")
	int id;

	@Id
	@Column(name = "project_id")
	int projectId;
	
	@Id
	@Column(name = "modul_id")
	int modulId;
	
	@Id
	@Column(name = "file_name")
	String fileName;
	
	@Id
	@Column(name = "user_id")
	int userId;
	
	@Column(name = "file_size")
	int fileSize;
	
	@Column(name = "description_file")
	String descriptionFile;
	
	String extension;
	
	@Column(name = "upload_date")
	Date uploadDate;
	
	@Column(name = "modul_name")
	String moduleName;
	
	@Column(name = "project_name")
	String projectName;
	
	@Column(name = "path")
	String path;
	
	@Column(name = "user_id_module")
	int userIdModule;
	
	String pic;
	
	@Column(name = "user_name")
	String userName;

	@Column(name = "url_path")
	String urlPath;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getModulId() {
		return modulId;
	}

	public void setModuleId(int moduleId) {
		this.modulId = moduleId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getDescriptionFile() {
		return descriptionFile;
	}

	public void setDescriptionFile(String descriptionFile) {
		this.descriptionFile = descriptionFile;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uplodaDate) {
		this.uploadDate = uplodaDate;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setModulId(int modulId) {
		this.modulId = modulId;
	}

	public int getUserIdModule() {
		return userIdModule;
	}

	public void setUserIdModule(int userIdModule) {
		this.userIdModule = userIdModule;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
}
