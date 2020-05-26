package com.example.model;

import java.util.ArrayList;
import java.util.List;

import com.example.model.view.ViewBugs;
import com.example.model.view.ViewDocumentFile;

public class UserDetail {
    List<ViewBugs> viewBugs = new ArrayList<ViewBugs>();
    List<DataModule> modul = new ArrayList<DataModule>();
    List<ViewDocumentFile> documentFile = new ArrayList<ViewDocumentFile>();

    public UserDetail(List<ViewBugs> viewBugs, List<DataModule> modul, List<ViewDocumentFile> documentFile){
        this.viewBugs = viewBugs;
        this.modul = modul;
        this.documentFile = documentFile;
    }

    public List<ViewBugs> getBugs() {
        return viewBugs;
    }

    public void setBugs(List<ViewBugs> viewBugs) {
        this.viewBugs = viewBugs;
    }

    public List<DataModule> getModul() {
        return modul;
    } 

    public void setModul(List<DataModule> modul) {
        this.modul = modul;
    }

    public List<ViewDocumentFile> getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(List<ViewDocumentFile> documentFile) {
        this.documentFile = documentFile;
    }

    
}