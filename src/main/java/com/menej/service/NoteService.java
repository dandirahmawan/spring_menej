package com.menej.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.model.db.Note;
import com.menej.model.view.ViewNote;
import com.menej.repo.NoteRepo;
import com.menej.repo.ViewNoteRepo;

@Service
public class NoteService {
	
	@Autowired
	NoteRepo nr;
	
	@Autowired
	ViewNoteRepo vnr;
	
	public ViewNote insertNote(int userId, int moduleId, String note, int bugsId) {
		String type = (bugsId > 0) ? "bugs" : "module";
		Date date = new Date();
		Note noteModel = new Note();
		if(moduleId > 0) noteModel.setModuleId(moduleId);
		if(bugsId > 0) noteModel.setBugsId(bugsId);
		noteModel.setNote(note);
		noteModel.setUserId(userId);
		noteModel.setCreatedDate(date);
		noteModel.setType(type);
		nr.save(noteModel);
		return vnr.findByUserIdAndModuleIdAndCreatedDate(userId, moduleId, date);
	}

	public String editNote(int noteId, String note){
		Note noteModel = nr.findByNoteId(noteId);
		noteModel.setNote(note);
		nr.save(noteModel);
		return null;
	}
	
	public List<ViewNote> getViewNoteProject(int projectId){
		return vnr.findByProjectId(projectId);
	}
	
	public int deleteNote(int noteId) {
		return nr.deleteNote(noteId);
	}
}
