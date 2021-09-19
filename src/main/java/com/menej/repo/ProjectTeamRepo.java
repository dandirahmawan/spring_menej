package com.menej.repo;


import java.util.List;

import javax.transaction.Transactional;

import com.menej.model.db.ProjectTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProjectTeamRepo extends JpaRepository<ProjectTeam, String>{
	List<ProjectTeam> findAll();

	@Query("SELECT pt FROM ProjectTeam pt JOIN Project p ON pt.projectId = p.projectId \n" +
			"WHERE (pt.projectId = ?1 AND pt.userId = ?2 AND (pt.userStatus IS NULL OR pt.userStatus = ?3)) \n " +
			"OR (p.pic = ?2 AND p.projectId = ?1 AND (p.isDelete IS NULL OR p.isDelete != 'Y'))")
	List<ProjectTeam> findByProjectIdAndUserIdAndUserStatusQry(int projectId, int userId, String userStatus);

	@Transactional
	@Modifying
	int deleteProjectTeamByUserIdAndProjectId(int userId, int projectId);

	List<ProjectTeam> findByProjectIdAndUserId(int projectId, int userId);
	
	String sql = "UPDATE FROM ProjectTeam SET userStatus = 'N' WHERE projectId = ?1 AND userId = ?2";
	@Transactional
	@Modifying
	@Query(sql)
	int deleteMember(int projectId, int userId);
	
	//for delete when user delete user from page user
//	String sqlDeleteUser = "DELETE \n"
//				+ "FROM \n"
//				+ "ProjectTeam pt \n"
//				+ "WHERE pt.projectId IN ("
//				+ "	SELECT \n"
//				+ "		pt1.projectId \n"
//				+ "	FROM \n"
//				+ "		(SELECT pt0 FROM ProjectTeam pt0) pt1 \n"
//				+ "	JOIN Project p ON p.projectId = pt1.projectId \n"
//				+ "WHERE p.pic = ?1\n"
//				+ "	AND (p.isDelete != 'Y' OR p.isDelete IS NULL)"
//				+ ") \n"
//				+ "AND pt.userId = ?2";
//	@Transactional
//	@Modifying
//	@Query(sqlDeleteUser)
//	int deleteUser(String pic, int userId);
}
