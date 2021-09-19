package com.menej.repo;

import java.util.List;

import com.menej.model.BugsUserDetail;
import com.menej.model.db.Bugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BugsRepo extends JpaRepository<Bugs, String>{
	List<Bugs> findBymodulId(int id);
	List<Bugs> findByProjectId(int id);
	Bugs findByBugsId(int id);
	Bugs findByModulIdAndNote(int id, String note);
	
	@Query("SELECT new com.menej.model.BugsUserDetail(" +
			"	b.note, " +
			"	b.createDate" +
			") " +
			"FROM Bugs b " +
			"JOIN AssignedModule am ON am.moduleId = b.modulId " +
			"JOIN Modul m ON m.modulId = am.moduleId " +
			"WHERE am.userId = ?1 AND (m.isTrash = 'N' OR m.isTrash IS NULL)")
	List<BugsUserDetail> findByUserId(int userId);
}
