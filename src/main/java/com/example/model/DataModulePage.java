package com.example.model;

import java.util.List;

import com.example.model.view.ViewNote;

public class DataModulePage {
    List<DataModule> dataModule;
    List<DataProject> dataProject;
    List<DataProjectTeam> dataProjectTeam;
    List<PermitionProject> permitionProjects;
    List<ViewNote> note;
    List<DataTab> tabs;

    public List<DataModule> getDataModule() {
        return dataModule;
    }

    public void setDataModule(List<DataModule> dataModule) {
        this.dataModule = dataModule;
    }

    public List<DataProject> getDataProject() {
        return dataProject; 
    }

    public void setDataProject(List<DataProject> dataProject) {
        this.dataProject = dataProject;
    }

    public List<DataProjectTeam> getDataProjectTeam() {
        return dataProjectTeam;
    }

	public void setDataProjectTeam(List<DataProjectTeam> dataProjectTeam) {
        this.dataProjectTeam = dataProjectTeam;
    }
    
	public List<PermitionProject> getPermitionProjects() {
		return permitionProjects;
	}

	public void setPermitionProjects(List<PermitionProject> permitionProjects) {
		this.permitionProjects = permitionProjects;
	}

	public List<ViewNote> getNote() {
		return note;
	}

	public void setNote(List<ViewNote> note) {
		this.note = note;
	}

    public List<DataTab> getTabs() {
        return tabs;
    }

    public void setTabs(List<DataTab> tabs) {
        this.tabs = tabs;
    }
}