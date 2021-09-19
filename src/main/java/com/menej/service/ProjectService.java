package com.menej.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.menej.DBConnection;
import com.menej.model.DataProject;
import com.menej.model.db.Project;
import com.menej.model.db.ProjectTeam;
import com.menej.repo.ProjectTeamRepo;
import net.bytebuddy.asm.Advice;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.repo.ProjectRepo;

@Service
public class ProjectService {
	@Autowired
	ProjectRepo pr;

	@Autowired
	ProjectTeamRepo ptr;

	public List<Project> getDataProject(){
		List<Project> data = pr.findAll();
		return data;
	}
	
	public List<DataProject> getDataListProject(String userId){
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
		if(project.size() > 0){
			for(Project row : project) {
				System.out.print("project name delete : "+row.getProjectName());
				row.setIsDelete("Y");
				pr.save(row);
				rtn = "success";
			}
		}else{
			rtn = "You did not have privilege to delete this project";
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

	public String editProject(int projectId, String projectName, int pic){
	    Project project = pr.findByProjectId(projectId);
	    String currentPic = project.getPic();

	    project.setProjectName(projectName);
	    project.setPic(String.valueOf(pic));
	    pr.save(project);

	    List<ProjectTeam> projectTeamList = ptr.findByProjectIdAndUserId(projectId, Integer.parseInt(currentPic));
	    if(pic != Integer.parseInt(currentPic)){
	    	/*
	    	delete user team member, cause the user will be set as pic
	    	(pic not set as team member)
	    	 */
			ptr.deleteProjectTeamByUserIdAndProjectId(pic, projectId);

	    	ProjectTeam projectTeam = new ProjectTeam();
	    	projectTeam.setUserId(Integer.parseInt(currentPic));
	    	projectTeam.setProjectId(projectId);
	    	projectTeam.setCreatedDate(new Date());
			ptr.save(projectTeam);
	    }

	    return "success";
    }
}
