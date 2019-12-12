package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Bugs;
import com.example.model.BugsUserDetail;

public interface BugsRepo extends JpaRepository<Bugs, String>{
	List<Bugs> findBymodulId(int id);
	List<Bugs> findByProjectId(int id);
	Bugs findByModulIdAndNote(int id, String note);
	
	@Query("SELECT new com.example.model.BugsUserDetail(b.note, b.createDate) FROM Bugs b JOIN Modul m ON m.modulId = b.modulId WHERE m.userId = ?1 AND (m.isTrash = 'N' OR m.isTrash IS NULL)")
	List<BugsUserDetail> findByUserId(int userId);
}
