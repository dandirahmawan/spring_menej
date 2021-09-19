package com.menej.repo;

import javax.transaction.Transactional;

import com.menej.model.db.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NoteRepo extends JpaRepository<Note, Integer>{
	Note findByNoteId(int noteId);
	
	String sql = "DELETE Note WHERE noteId = ?1";
	@Transactional
	@Modifying
	@Query(sql)
	int deleteNote(int noteId);
}
