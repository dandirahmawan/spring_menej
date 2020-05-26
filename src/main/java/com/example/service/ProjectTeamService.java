package com.example.service;

import java.util.Date;
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
	
	public String delete(int userId, int projectId) {
//		List<ProjectTeam> ptAll = prt.findByUserId(14);
//		System.out.println(ptAll.size());
//		for(int i = 0;i<ptAll.size();i++) {
//			System.out.println(ptAll.get(i).getUserId());
//			System.out.println(ptAll.get(i).getProjectId());
//			System.out.println("-------------------------");
//		}
		int del = prt.deleteMember(userId, projectId);
		System.out.println("====== "+del);
		return null;
	}
}
