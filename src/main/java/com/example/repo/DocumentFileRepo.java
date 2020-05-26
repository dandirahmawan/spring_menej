package com.example.repo;

import java.util.List;

import javax.transaction.Transactional;

import com.example.model.DocumentFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DocumentFileRepo extends JpaRepository<DocumentFile, Integer>{
    List<DocumentFile> findByModulId(int id);
    List<DocumentFile> findByProjectId(int id);
    List<DocumentFile> findByUserId(int id);
    List<DocumentFile> findByModulIdAndProjectIdAndFileName(int moduleId, int projectId, String fileName);

    //delete doc file
    String sql = "DELETE DocumentFile WHERE modulId = ?1 AND projectId = ?2 AND fileName = ?3";
    @Transactional
    @Modifying
    @Query(sql)
    int deleteDocumentFile(int modulId, int projectId, String fileName);
}