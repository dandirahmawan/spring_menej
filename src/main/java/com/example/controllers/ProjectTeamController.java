package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.DataProjectTeam;
import com.example.service.ProjectTeamService;

@RestController
public class ProjectTeamController {
	
	@Autowired
	ProjectTeamService prs;
	
	@PostMapping("/team")
	public List<DataProjectTeam> getDate(@RequestParam int project_id){
		return prs.getDataProjectTeam(project_id);
	}
}
