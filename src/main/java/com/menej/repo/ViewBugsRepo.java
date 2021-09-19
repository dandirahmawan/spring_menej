package com.menej.repo;

import java.util.Date;
import java.util.List;

import com.menej.model.view.ViewBugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewBugsRepo extends JpaRepository<ViewBugs, Integer>{
	List<ViewBugs> findAll();

	@Query("SELECT vb FROM ViewBugs vb WHERE modulId = ?1 AND (isDelete IS NULL  OR isDelete <> 'Y')")
	List<ViewBugs> findByModulId(int moduleId);

	ViewBugs findByModulIdAndNoteAndCreateDate(int moduleId, String note, Date date);
	
	@Query("SELECT vb FROM ViewBugs vb WHERE projectId = ?1 AND (userId = ?2 OR pic = ?3)")
	List<ViewBugs> findByProjectId(int projectId, int userId1, int userId2);
	
	@Query("SELECT vb FROM ViewBugs vb WHERE vb.pic = ?1 OR vb.userId = ?2")
	List<ViewBugs> findQueryByUserId(int userId, int userId2);
	
	@Query("SELECT vb FROM ViewBugs vb WHERE vb.pic = ?1 AND vb.userId = ?2")
	List<ViewBugs> findQueryByUserIdDetail(int userId, int userId2);
}
