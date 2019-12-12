package com.example.model;

import java.util.List;

public class DataModulePage {
    List<DataModule> dataModule;
    List<DataProject> dataProject;
    List<DataProjectTeam> dataProjectTeam;

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

    
    
}