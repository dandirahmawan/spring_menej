package com.example.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.DataProject;
import com.example.model.Project;

public interface ProjectRepo extends JpaRepository<Project, String>{
	List<Project> findAll();
	List<Project> findByProjectIdAndCreatedBy(int projectId, int userId);

	String sql = "SELECT new com.example.model.DataProject("
			+ "p.projectId, \n"
			+ "p.pic, \n"
			+ "p.projectName, \n"
			+ "p.createdBy, \n"
			+ "p.createdDate, \n"
			+ "p.isDelete, \n"
			+ "p.isClose, \n"
			+ "u.userName, \n"
			+ "(SELECT COUNT(projectId) FROM ProjectTeam WHERE projectId = p.projectId) AS countTeam, \n"
			+ "(SELECT userName FROM User WHERE user_id = p.pic) AS picName, \n"
			+ "(SELECT COUNT(projectId) FROM Bugs WHERE projectId = p.projectId) AS countBugs, \n"
			+ "(SELECT COUNT(modulId) FROM Modul WHERE projectId = p.projectId and (isTrash = 'N' or isTrash IS NULL)) AS countModule) \n"
			+ "FROM Project p \n"
			+ "LEFT JOIN User u ON p.createdBy = u.user_id \n"
			+ "WHERE (p.isDelete <> 'Y' OR p.isDelete IS NULL)";

	@Query(sql)
	List<DataProject> fetchDataProject();
	
	String where = "AND p.projectName = ?1 AND p.createdDate = ?2 AND p.createdBy = ?3";
	@Query(sql+" "+where)
	List<DataProject> findByProjectNameAndCreatedDateAndCreatedBy(String projectName, Date date, int createdBy);

	String where1 = "AND p.projectId = ?1";
	@Query(sql+" "+where1)
	List<DataProject> findByProjectId(int projectId);
}
