package com.menej.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menej.Utils;
import com.menej.model.db.Bugs;
import com.menej.model.view.ViewNote;
import com.menej.service.BugsService;
import com.menej.service.NoteService;

@RestController
public class NoteController {
	
	@Autowired
	NoteService ns;
	
	@Autowired
	BugsService bs;
	
	@PostMapping("/insert_note")
	public ViewNote insertNote(int userId, int moduleId, int bugsId, String sessionId, String note) {
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		
		if(bugsId > 0) {
			Bugs bugs = new Bugs();
			bugs = bs.getBugsByBugsId(bugsId);
			moduleId = bugs.getModulId();
			System.out.println("module id => "+moduleId+" by bugsId => "+bugsId);
		}
		
		ViewNote viewNote = new ViewNote();
		if(isAccess) {
			viewNote = ns.insertNote(userId, moduleId, note, bugsId);
		}
		
		return viewNote;
	}
	
	@PostMapping("/delete_note")
	public int DeleteNote(int noteId, int userId, String sessionId) {
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		int delResult = 0;
		if(isAccess) {
			delResult = ns.deleteNote(noteId);
		}
		return delResult;
	}

	@PostMapping("/edit_note")
	public String editNote(int userId, String sessionId, String note, int noteId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		String editResult = "0";
		if(isAccess) {
			editResult = ns.editNote(noteId, note);
		}
		return editResult;
	}
}
