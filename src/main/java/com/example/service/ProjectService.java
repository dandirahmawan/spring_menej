package com.example.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.DBConnection;
import com.example.model.User;
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
	
	public List<DataProject> getDataListProject(String userId){
		System.out.println("user id is = > "+userId);
		List<DataProject> dp = pr.fetchDataProject(userId, Integer.parseInt(userId));
		return dp;
	}

	public List<DataProject> getDataListProjectById(int projectId, int userId){
		List<DataProject> dp = pr.findByProjectId(projectId, userId);
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
	
	public String insertData(int userId, String projectName) {
		List<DataProject> numProject = pr.findExistsProject(userId, projectName);
		List<DataProject> dataProject = new ArrayList<DataProject>();
		String rtn = "";
		if(numProject.size() < 1) {
			Project project = new Project();
			Date date = new Date();
			project = new Project();
			project.setProjectName(projectName);
			project.setCreatedDate(date);
			project.setCreatedBy(userId);
			project.setPic(String.valueOf(userId));
			pr.save(project);
			dataProject = pr.findByProjectNameAndCreatedDateAndCreatedBy(projectName, date, userId);
			for(DataProject item : dataProject) {
				rtn = String.valueOf(item.getProjectId());
			}
		}else {
			rtn = "exists";
		}
		return rtn;
	}

	public String handOver(int projectId, int userId){
		DBConnection gc = new DBConnection();
		Connection connection = gc.getConnection();
		PreparedStatement pr = null;

		String sql = "UPDATE project SET pic = ? WHERE project_id = ?";

		try{
			pr = connection.prepareStatement(sql);
			pr.setString(1, String.valueOf(userId));
			pr.setInt(2, projectId);
			pr.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try{
				if(connection != null) connection.close();
				if(pr != null) pr.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
}
