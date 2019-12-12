package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Bugs;
import com.example.service.BugsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BugsController {
	@Autowired
	BugsService bs;
	
	@GetMapping("/bugs")
	public List<Bugs> getBugs(){
		return bs.getBugs();
	}

	@PostMapping(value="/add_bugs")
	public Bugs addBugs(@RequestParam int projectId, int moduleId, String bugs){
		return bs.insertBugs(moduleId, projectId, bugs);
	}

	@PostMapping("/delete_bugs")
	public String deleteBugs(@RequestParam int moduleId, String note) {
		bs.deleteBugs(moduleId, note);
		return null;
	}
	
	
}
