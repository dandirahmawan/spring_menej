package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class UserDetail {
    List<BugsUserDetail> bugs = new ArrayList<BugsUserDetail>();
    List<DataModule> modul = new ArrayList<DataModule>();
    List<DocumentFile> documentFile = new ArrayList<DocumentFile>();

    public UserDetail(List<BugsUserDetail> bugs, List<DataModule> modul, List<DocumentFile> documentFile){
        this.bugs = bugs;
        this.modul = modul;
        this.documentFile = documentFile;
    }

    public List<BugsUserDetail> getBugs() {
        return bugs;
    }

    public void setBugs(List<BugsUserDetail> bugs) {
        this.bugs = bugs;
    }

    public List<DataModule> getModul() {
        return modul;
    }

    public void setModul(List<DataModule> modul) {
        this.modul = modul;
    }

    public List<DocumentFile> getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(List<DocumentFile> documentFile) {
        this.documentFile = documentFile;
    }

    
}