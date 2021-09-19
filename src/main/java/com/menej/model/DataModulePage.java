package com.menej.model;

import java.util.List;

import com.menej.model.db.*;
import com.menej.model.view.*;

public class DataModulePage {
//    List<DataModule> dataModule;
    List<Section> dataModule;
    List<DataProject> dataProject;
    List<DataProjectTeam> dataProjectTeam;
    List<PermitionProject> permitionProjects;
    List<ViewNote> note;
    List<DataTab> tabs;
    List<ViewStatusModule> statusModules;
    List<Labels> labelsList;
    List<ViewLabelModule> labelModulelist;
    List<ViewAssignedModule> assignedModules;
    List<SectionModule> sectionModules;

//    public List<DataModule> getDataModule() {
//        return dataModule;
//    }
//
//    public void setDataModule(List<DataModule> dataModule) {
//        this.dataModule = dataModule;
//    }

    public List<SectionModule> getSectionModules() {
        return sectionModules;
    }

    public void setSectionModules(List<SectionModule> sectionModules) {
        this.sectionModules = sectionModules;
    }

    public List<Section> getDataModule() {
        return dataModule;
    }

    public void setDataModule(List<Section> dataModule) {
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

    public List<ViewStatusModule> getStatusModules() {
        return statusModules;
    }

    public void setStatusModules(List<ViewStatusModule> statusModules) {
        this.statusModules = statusModules;
    }

    public List<Labels> getLabelsList() {
        return labelsList;
    }

    public void setLabelsList(List<Labels> labelsList) {
        this.labelsList = labelsList;
    }

    public List<ViewLabelModule> getLabelModulelist() {
        return labelModulelist;
    }

    public void setLabelModulelist(List<ViewLabelModule> labelModulelist) {
        this.labelModulelist = labelModulelist;
    }

    public List<ViewAssignedModule> getAssignedModules() {
        return assignedModules;
    }

    public void setAssignedModules(List<ViewAssignedModule> assignedModules) {
        this.assignedModules = assignedModules;
    }
}