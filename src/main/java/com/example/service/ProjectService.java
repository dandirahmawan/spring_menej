package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.DataProject;
import com.example.model.Project;
import com.example.repo.ProjectRepo;

@Service
public class ProjectService {
	@Autowired
	ProjectRepo pr;
	
	public List<Project> getDataProject(){
		List<Project> data = pr.findAll();
		return data;
	}
	
	public List<DataProject> getDataListProject(){
		List<DataProject> dp = pr.fetchDataProject();
		return dp;
	}

	public List<DataProject> getDataListProjectById(int projectId){
		List<DataProject> dp = pr.findByProjectId(projectId);
		return dp;
	}
	
	public String deleteProject(int projectId, int userId) {
		List<Project> project = pr.findByProjectIdAndCreatedBy(projectId, userId);
		String rtn = null;
		for(Project row : project) {
			row.setIsDelete("Y");
			pr.save(row);
			rtn = "success";
		}
		return rtn;
	}
	
	public List<DataProject> insertData(int userId, String projectName) {
		Project project = new Project();
		Date date = new Date();
		project = new Project();
		project.setProjectName(projectName);
		project.setCreatedDate(date);
		project.setCreatedBy(userId);
		project.setPic(String.valueOf(userId));
		pr.save(project);
		return pr.findByProjectNameAndCreatedDateAndCreatedBy(projectName, date, userId);
	}
}
