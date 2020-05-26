package com.example.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.DataModule;
import com.example.model.Modul;

public interface ModuleRepo extends JpaRepository<Modul, Integer>{
	List<Modul> findByProjectId(Integer id);
	List<Modul> findAll();

	String sql = "SELECT new com.example.model.DataModule"
					+"("
						+"m.projectId, \n"
						+"p.projectName, \n"
						+"m.modulId, \n"
						+"m.modulName, \n"
						+"m.userId, \n"
						+"p.pic, \n"
						+"usr.userName, \n"
						+"usr.emailUser, \n"
						+"m.createdDate, \n"
						+"m.createdBy, \n"
						+"CASE WHEN m.updatedBy IS NULL THEN '' ELSE  m.updatedBy END AS updatedBy, \n"
						+"CASE WHEN m.updatedDate IS NULL THEN '' ELSE  m.updatedDate END AS updatedDate, \n"
						+"m.endDate, \n"
						+"m.modulStatus, \n"
						+"CASE WHEN m.description IS NULL THEN '' ELSE  m.description END AS description, \n"
						+"m.isTrash, \n"
						+"m.percentage, \n"
						+"(SELECT count(*) FROM ViewNote WHERE moduleId = m.modulId) AS countNote, \n"
						+"(SELECT count(modulId) FROM Bugs WHERE modulId = m.modulId) AS countBugs, \n"
						+"(SELECT count(modulId) FROM Bugs WHERE modulId = m.modulId AND bugStatus = 'C') AS countBugsClose, \n"
						+"(SELECT count(modulId) FROM DocumentFile WHERE modulId = m.modulId) AS countDoc, \n"
						+"(SELECT count(*) FROM ProjectTeam WHERE projectId = m.projectId AND userId = m.userId AND userStatus IS NULL OR userStatus = 'Y') AS isMember \n"
					+") \n"
					+"FROM Modul m \n"
					+"JOIN User usr ON usr.userId = m.userId \n"
					+"JOIN Project p ON p.projectId = m.projectId";

	@Query(sql+" WHERE m.projectId = ?1 AND (m.userId = ?2 OR p.pic = ?3) AND (m.isTrash = 'N' OR m.isTrash IS NULL) ORDER BY m.createdDate ASC")
	List<DataModule> fetchDataModuleByUser(int pi, int userId, String pic);
	
	@Query(sql+" WHERE m.projectId = ?1 AND m.modulId = ?2 ORDER BY m.createdDate ASC")
	DataModule fetchDataModuleByModuleId(int pi, int moduleId);
	
	@Query(sql+" WHERE (m.userId = ?1 OR p.pic = ?2) AND (m.isTrash = 'N' OR m.isTrash IS NULL)")
	List<DataModule> fetchDataModuleByUserId(int userId, String userId2);

	@Query(sql+" WHERE m.createdBy = ?1 AND m.createdDate = ?2 AND m.modulName = ?3 AND m.projectId = ?4")
	List<DataModule> fetchLast(int userLogin, Date date, String moduleName, int projectId);
	
	@Query(sql+" WHERE p.pic = ?1 AND m.userId = ?2 AND (m.isTrash = 'N' OR m.isTrash IS NULL)")
	List<DataModule> fetchDataUserDetail(String userLogin, int userid);

	Modul findBymodulId(Integer modulId);
}
