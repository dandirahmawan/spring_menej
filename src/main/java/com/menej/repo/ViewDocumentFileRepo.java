package com.menej.repo;

import java.util.Date;
import java.util.List;

import com.menej.model.view.ViewDocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewDocumentFileRepo extends JpaRepository<ViewDocumentFile, Integer>{
	
	@Query("SELECT vdf FROM ViewDocumentFile vdf WHERE (userIdModule = ?1 OR pic = ?2) AND projectId = ?3")
	List<ViewDocumentFile> findByProjectIdByOrderByUploadDateAsc(int userId, String userId2, int projectId);
	
	@Query("SELECT vdf FROM ViewDocumentFile vdf WHERE userIdModule = ?1 OR pic = ?2")
	List<ViewDocumentFile> findByUserId(int userId, String userId2);
	
	@Query("SELECT vdf FROM ViewDocumentFile vdf WHERE userIdModule = ?1 AND pic = ?2")
	List<ViewDocumentFile> findByUserIdDetail(int userId, String userLogin);

	@Query("SELECT vdf FROM ViewDocumentFile vdf WHERE vdf.modulId = ?1")
	List<ViewDocumentFile> findByModuleId(int moduleId);
	
	ViewDocumentFile findByModulIdAndProjectIdAndUserIdAndUploadDate(int moduleId, int projectId, int userId, Date date);
}
