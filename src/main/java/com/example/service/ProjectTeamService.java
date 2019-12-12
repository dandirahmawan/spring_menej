package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.DataProjectTeam;
import com.example.model.ProjectTeam;
import com.example.repo.DataProjectTeamRepo;
import com.example.repo.ProjectTeamRepo;

@Service
public class ProjectTeamService {
	@Autowired
	ProjectTeamRepo prt;

	@Autowired 
	DataProjectTeamRepo dptr;
	
	public List<ProjectTeam> getData(){
		return prt.findAll();
	}

	public List<DataProjectTeam> getDataProjectTeam(int projectId){
		return dptr.findByProjectId(projectId);
	}
}
