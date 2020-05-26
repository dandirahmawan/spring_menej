package com.example.model;

import java.util.List;

import com.example.model.view.ViewBugs;

public class DetailModule{
    private List<ViewBugs> bugs;
    private List<DocumentFile> documentFile;
    private List<PermitionProject> permitionProject;
    private DataModule dataModule;
    
    public DetailModule(List<ViewBugs> bugs, List<DocumentFile> documentFile, List<PermitionProject> permitionProjects, DataModule dataModule){
        this.bugs = bugs;
        this.documentFile = documentFile;
        this.permitionProject = permitionProjects;
        this.dataModule = dataModule;
    }

    public List<ViewBugs> getBugs() {
        return bugs;
    }

    public void setBugs(List<ViewBugs> bugs) {
        this.bugs = bugs;
    } 

    public List<DocumentFile> getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(List<DocumentFile> documentFile) {
        this.documentFile = documentFile;
    }

	public List<PermitionProject> getPermitionProject() {
		return permitionProject;
	}

	public void setPermitionProject(List<PermitionProject> permitionProject) {
		this.permitionProject = permitionProject;
	}

	public DataModule getDataModule() {
		return dataModule;
	}

	public void setDataModule(DataModule dataModule) {
		this.dataModule = dataModule;
	}

    
}