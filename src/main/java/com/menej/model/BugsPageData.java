package com.menej.model;

import java.util.List;

import com.menej.model.view.ViewBugs;
import com.menej.model.view.ViewNote;

public class BugsPageData {
	List<ViewBugs> bugs;
	List<ViewNote> note;
	
	public BugsPageData(List<ViewBugs> bugs, List<ViewNote> note) {
		this.bugs = bugs;
		this.note = note;
	}
	
	public List<ViewBugs> getBugs() {
		return bugs;
	}
	public void setBugs(List<ViewBugs> bugs) {
		this.bugs = bugs;
	}
	public List<ViewNote> getNote() {
		return note;
	}
	public void setNote(List<ViewNote> note) {
		this.note = note;
	}
	
	
}
