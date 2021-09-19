package com.menej.repo;

import java.util.Date;
import java.util.List;

import com.menej.model.view.ViewNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewNoteRepo extends JpaRepository<ViewNote, Integer>{
	List<ViewNote> findByProjectId(int projectId);
	List<ViewNote> findByUserIdAndModuleId(int userId, int moduleId);
	List<ViewNote> findByUserId(int userId);
	List<ViewNote> findByModuleId(int moduleId);
	ViewNote findByUserIdAndModuleIdAndCreatedDate(int userId, int moduleId, Date newDate);
}
