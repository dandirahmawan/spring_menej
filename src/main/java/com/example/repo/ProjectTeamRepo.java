package com.example.repo;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.model.ProjectTeam;

public interface ProjectTeamRepo extends JpaRepository<ProjectTeam, String>{
	List<ProjectTeam> findAll();

	@Query("SELECT pt FROM ProjectTeam pt JOIN Project p ON pt.projectId = p.projectId \n" +
			"WHERE (pt.projectId = ?1 AND pt.userId = ?2 AND (pt.userStatus IS NULL OR pt.userStatus = ?3)) \n " +
			"OR (p.pic = ?2 AND p.projectId = ?1 AND (p.isDelete IS NULL OR p.isDelete != 'Y'))")
	List<ProjectTeam> findByProjectIdAndUserIdAndUserStatusQry(int projectId, int userId, String userStatus);
	
	String sql = "UPDATE FROM ProjectTeam SET userStatus = 'N' WHERE userId = ?1 AND projectId = ?2";
	@Transactional
	@Modifying
	@Query(sql)
	int deleteMember(int userId, int projectId);
	
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
