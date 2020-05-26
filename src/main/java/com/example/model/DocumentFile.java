package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.keys.DocumentFileKeys;

@Entity
@Table(name = "document_file")
@IdClass(DocumentFileKeys.class)
public class DocumentFile {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Id
    @Column(name = "modul_id")
    private int modulId;

    @Id
    @Column(name = "file_name")
    private String fileName;

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "description_file")
    private String description;

    @Column(name = "file_size")
    private int fileSize;

    @Column(name = "extension")
    private String extension;

    @Column(name = "upload_date")
    private Date uploadDate;
    
    String path;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
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

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
}