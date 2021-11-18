package com.menej.repo;

import java.util.Date;
import java.util.List;

import com.menej.model.DataProject;
import com.menej.model.db.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepo extends JpaRepository<Project, String>{
	List<Project> findByProjectIdAndCreatedBy(int projectId, int userId);
    Project findByProjectId(int projectId);

	String sql = "SELECT DISTINCT new com.menej.model.DataProject("
			+ "p.projectId, \n"
			+ "p.pic, \n"
			+ "p.projectName, \n"
			+ "p.createdBy, \n"
			+ "p.createdDate, \n"
			+ "p.isDelete, \n"
			+ "p.isClose, \n"
			+ "u.userName, \n"
			+ "(SELECT COUNT(projectId) FROM ProjectTeam WHERE projectId = p.projectId) AS countTeam, \n"
			+ "u.userName AS picName, \n"
			+ "u.emailUser AS picEmail, \n"
			+ "(SELECT COUNT(projectId) FROM Bugs WHERE projectId = p.projectId AND (isDelete = 'N' OR isDelete IS NULL)) AS countBugs, \n"
			+ "(SELECT COUNT(modulId) FROM Modul WHERE projectId = p.projectId and (isTrash = 'N' or isTrash IS NULL)) AS countModule) \n"
			+ "FROM Project p \n"
			+ "LEFT JOIN ProjectTeam pt ON pt.projectId = p.projectId \n"
			+ "LEFT JOIN User u ON p.pic = u.userId \n"
			+ "WHERE (p.isDelete <> 'Y' OR p.isDelete IS NULL)";

	@Query(sql+" AND (p.pic = ?1 OR p.projectId IN (SELECT \r\n" +
			"											pt.projectId \r\n" +
			"										FROM ProjectTeam pt \r\n" +
			"										WHERE pt.userId = ?2 AND (pt.userStatus IS NULL OR pt.userStatus != 'N'))) \r\n" +
			"ORDER BY p.projectName ASC")
	List<DataProject> fetchDataProject(String userId, int userId2);
	
	String where = "AND p.projectName = ?1 AND p.createdDate = ?2 AND p.createdBy = ?3";
	@Query(sql+" "+where)
	List<DataProject> findByProjectNameAndCreatedDateAndCreatedBy(String projectName, Date date, int createdBy);

	String where1 = "AND p.projectId = ?1 AND ((pt.userId = ?2 AND (pt.userStatus IS NULL OR pt.userStatus != 'N')) OR p.pic = ?2)";
	@Query(sql+" "+where1)
	List<DataProject> findByProjectId(int projectId, int userId);
	
	String where2 = "AND p.createdBy = ?1 AND p.projectName = ?2";
	@Query(sql+" "+where2)
	List<DataProject> findExistsProject(int userId, String projectName);
}
