package com.menej.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.model.view.DataProjectTeam;
import com.menej.model.db.ProjectTeam;
import com.menej.repo.DataProjectTeamRepo;
import com.menej.repo.ProjectTeamRepo;

@Service
public class ProjectTeamService {
	@Autowired
	ProjectTeamRepo prt;

	@Autowired 
	DataProjectTeamRepo dptr;
	
	@Autowired
	PermitionService ps;
	
	public List<ProjectTeam> getData(){
		return prt.findAll();
	}

	public List<DataProjectTeam> getDataProjectTeam(int projectId){
		return dptr.findByProjectIdQry(projectId);
	}
	
	public String insertData(int projectIdInt, int userIdInt) {
		ps.insertPermition(userIdInt, projectIdInt, 2);
		ProjectTeam pt = new ProjectTeam();
		pt.setProjectId(projectIdInt);
		pt.setUserId(userIdInt);
		pt.setCreatedDate(new Date());
		pt.setUserStatus(null);
		prt.save(pt);
		return null;
	}
	
	public int delete(int userId, int projectId) {
		int del = prt.deleteMember(projectId, userId);
		return del;
	}
}
