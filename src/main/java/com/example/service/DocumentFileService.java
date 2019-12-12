package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.model.DocumentFile;
import com.example.repo.DocumentFileRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentFileService{
    @Autowired
    DocumentFileRepo dfr;

    public DocumentFile upload(int projectId, int moduleId, int userId, Long size, String extension, String fileName){
        DocumentFile df = new DocumentFile();
        Date date = new Date();

        df.setProjectId(projectId);
        df.setModulId(moduleId);
        df.setUserId(userId);
        df.setFileSize(Integer.parseInt(size.toString()));
        df.setExtension(extension);
        df.setUploadDate(date);
        df.setFileName(fileName);
        dfr.save(df);

        return df;
    }

    public List<DocumentFile> getDataListDocFile(int moduleId){
        return dfr.findByModulId(moduleId);
    }

    public int delete(int modulId, int projectId, String fileName){
        int del = dfr.deleteDocumentFile(modulId, projectId, fileName);
        return del;
    }

    public List<DocumentFile> getDocByUser(int userId){
        return dfr.findByUserId(userId);
    }
}