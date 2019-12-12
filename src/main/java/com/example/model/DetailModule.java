package com.example.model;

import java.util.List;

public class DetailModule{
    private List<Bugs> bugs;
    private List<DocumentFile> documentFile;
    
    public DetailModule(List<Bugs> bugs, List<DocumentFile> documentFile){
        this.bugs = bugs;
        this.documentFile = documentFile;
    }

    public List<Bugs> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bugs> bugs) {
        this.bugs = bugs;
    }

    public List<DocumentFile> getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(List<DocumentFile> documentFile) {
        this.documentFile = documentFile;
    }

    
}