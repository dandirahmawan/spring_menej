package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utils;
import com.example.model.Bugs;
import com.example.model.BugsPageData;
import com.example.model.view.ViewBugs;
import com.example.model.view.ViewNote;
import com.example.service.BugsService;
import com.example.service.NoteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BugsController {
	@Autowired
	BugsService bs;
	
	@Autowired
	NoteService ns;
	
	@GetMapping("/bugs")
	public List<Bugs> getBugs(){
		return bs.getBugs(); 
	}
	
	@PostMapping("/bugs_project")
	public BugsPageData getViewBugsData(int userId, int projectId, String sessionId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		List<ViewBugs> viewBugs = new ArrayList<ViewBugs>();
		List<ViewNote> viewNote = new ArrayList<ViewNote>();
		BugsPageData bugsPageData = null;
		if(isAccess) {
			viewBugs = bs.getViewBugsByProjectId(projectId, userId);
			viewNote = ns.getViewNoteProject(projectId); 
			bugsPageData = new BugsPageData(viewBugs, viewNote);
		}
		return bugsPageData;
	}

	@PostMapping(value="/add_bugs")
	public ViewBugs addBugs(@RequestParam int projectId, int moduleId, String bugs, int userId, String sessionId){
		ViewBugs viewBugs = null;
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		if(isAccess){
			viewBugs = bs.insertBugs(moduleId, projectId, bugs, userId);
		}
		return viewBugs;
	}

	@PostMapping("/delete_bugs")
	public String deleteBugs(@RequestParam int bugsId, String sessionId, int userId) {
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		if(isAccess) {
			bs.deleteBugs(bugsId);
		}
		return null;
	}
	
	@PostMapping("/close_bugs")
	public String close(@RequestParam int bugsId, String sessionId, int userId) {
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		if(isAccess) {
			bs.closeBugs(bugsId);
		}
		return null;
	}
	
	@PostMapping("/unclose_bugs")
	public String unclose(@RequestParam int bugsId, String sessionId, int userId) {
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		if(isAccess) {
			bs.uncloseBugs(bugsId);
		}
		return null;
	}
	
	@PostMapping("/bugs_user")
	public List<ViewBugs> getBugsUser(int userId, String sessionId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		
		List<ViewBugs> bugsList = new ArrayList<ViewBugs>();
		if(isAccess) {
			bugsList = bs.getViewBugsByUserId(userId);
		}
		return bugsList;
	}

	@PostMapping("/edit_bugs")
    public String editBugse(int userId, String sessionId, int bugsId, String bugs){
	    String rtn = null;
	    Utils utils = new Utils();
	    Boolean isAccess = utils.getAccess(userId, sessionId);
	    if(isAccess){
	        bs.editBugs(bugsId, bugs);
           rtn = "success";
	    }
	    return rtn;
    }
}
