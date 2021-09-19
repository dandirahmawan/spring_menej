package com.menej.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menej.Utils;
import com.menej.model.DataProject;
import com.menej.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	ProjectService ps;
	
	@PostMapping("/list")
	public List<DataProject> getDataListProject(@RequestParam int userId /*String sessionId*/){
		List<DataProject> data = new ArrayList<DataProject>();
		data = ps.getDataListProject(String.valueOf(userId));
		return data;  
	}
	
	@PostMapping("/insert_project")
	public String insert(@RequestParam int userId, String projectName) {
		return ps.insertData(userId, projectName);
	}
	
	@PostMapping("/delete_project")
	public String deleteProject(@RequestParam int projectId, int userId) {
		return ps.deleteProject(projectId, userId);
	}

	@PostMapping("/handover_project")

	public List<DataProject> handover(String sessionId, int userId_, int userId, int projectId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId_, sessionId);
		if(isAccess){
			ps.handOver(projectId, userId);
		}

		List<DataProject> dataProjects = new ArrayList<DataProject>();
		dataProjects = ps.getDataListProject(String.valueOf(userId_));
		return dataProjects;
	}

	@PostMapping("/edit_project")
	public String editProject(int projectId, String projectName, int pic){
		ps.editProject(projectId, projectName, pic);
		return "success";
	}
}
