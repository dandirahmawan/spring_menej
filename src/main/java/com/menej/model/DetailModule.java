package com.menej.model;

import java.util.List;

import com.menej.model.db.DocumentFile;
import com.menej.model.view.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailModule{
    private List<ViewBugs> bugs;
//    private List<DocumentFile> documentFile;
    private List<PermitionProject> permitionProject;
    private ViewModule dataModule;
    private List<ViewStatusModule> dataStatus;
    private List<ViewAssignedModule> assignedModules;
    private List<ViewLabelModule> labelModules;
    private List<ViewDocumentFile> documentFile;
    
    public DetailModule(List<ViewBugs> bugs,
//                        List<DocumentFile> documentFile,
                        List<ViewDocumentFile> viewDocumentFiles,
                        List<PermitionProject> permitionProjects,
                        ViewModule dataModule,
                        List<ViewStatusModule> dataStatus,
                        List<ViewAssignedModule> assignedModules,
                        List<ViewLabelModule> labelModules){

        this.bugs = bugs;
//        this.documentFile = documentFile;
        this.documentFile = viewDocumentFiles;
        this.permitionProject = permitionProjects;
        this.dataModule = dataModule;
        this.dataStatus = dataStatus;
        this.assignedModules = assignedModules;
        this.labelModules = labelModules;
    }
}