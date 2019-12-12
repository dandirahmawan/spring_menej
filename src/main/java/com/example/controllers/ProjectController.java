package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utils;
import com.example.model.DataProject;
import com.example.service.ProjectService;

@RestController
public class ProjectController {
	@Autowired
	ProjectService ps;
	
	@PostMapping("/list_project")
	public List<DataProject> getDataListProject(@RequestParam int userId, String sessionId){
		Utils util = new Utils();
		Boolean isAcces = util.getAccess(userId, sessionId);
		List<DataProject> data = new ArrayList<DataProject>();
		if(isAcces){
			data = ps.getDataListProject();
		}
		return data;
	}
	
	@PostMapping("/insert_project")
	public List<DataProject> insert(@RequestParam int userId, String projectName) {
		return ps.insertData(userId, projectName);
	}
	
	@PostMapping("/delete_project")
	public String deleteProject(@RequestParam int projectId, int userId) {
		return ps.deleteProject(projectId, userId);
		
	}
	
}
