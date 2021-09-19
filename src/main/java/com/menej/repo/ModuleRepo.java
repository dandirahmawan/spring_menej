package com.menej.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.menej.model.DataModule;
import com.menej.model.db.Modul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModuleRepo extends JpaRepository<Modul, Integer>{
	List<Modul> findByProjectId(Integer id);
	List<Modul> findAll();
	Optional<Modul> findTopByProjectIdOrderByModulIdDesc(int projectId);
	Optional<Modul> findTopByProjectIdAndCreatedByOrderByModulIdDesc(int projectId, int createdBy);

	String sql = "SELECT new com.menej.model.DataModule"
					+"("
						+"m.projectId, \n"
						+"p.projectName, \n"
						+"m.modulId, \n"
						+"m.modulName, \n"
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
						+"(SELECT count(*) FROM DataProjectTeam WHERE projectId = m.projectId AND userId = am.userId AND (userStatus IS NULL OR userStatus <> 'Y')) AS isMember \n"
					+") \n"
					+"FROM Modul m \n"
					+"JOIN AssignedModule am ON m.modulId = am.moduleId\n"
					+"JOIN User usr ON usr.userId = am.userId \n"
					+"JOIN Project p ON p.projectId = m.projectId";

	@Query(sql+" WHERE m.projectId = ?1 AND (am.userId = ?2 OR p.pic = ?3) AND (m.isTrash = 'N' OR m.isTrash IS NULL) GROUP BY m.modulId ORDER BY m.createdDate ASC")
	List<DataModule> fetchDataModuleByUser(int pi, int userId, String pic);

	@Query(sql+" WHERE m.projectId = ?1 AND (m.isTrash = 'N' OR m.isTrash IS NULL) GROUP BY m.modulId ORDER BY m.createdDate ASC")
	List<DataModule> fetchDataModuleByProjctId(int pi);
	
	@Query(sql+" WHERE m.projectId = ?1 AND m.modulId = ?2 GROUP BY m.modulId ORDER BY m.createdDate ASC")
	DataModule fetchDataModuleByModuleId(int pi, int moduleId);
	
	@Query(sql+" WHERE (am.userId = ?1 OR p.pic = ?2) AND (m.isTrash = 'N' OR m.isTrash IS NULL) GROUP BY m.modulId")
	List<DataModule> fetchDataModuleByUserId(int userId, String userId2);

	@Query(sql+" WHERE m.createdBy = ?1 AND m.createdDate = ?2 AND m.modulName = ?3 AND m.projectId = ?4 GROUP BY m.modulId")
	Optional<DataModule> fetchLast(int userLogin, Date date, String moduleName, int projectId);
	
	@Query(sql+" WHERE p.pic = ?1 AND am.userId = ?2 AND (m.isTrash = 'N' OR m.isTrash IS NULL) GROUP BY m.modulId")
	List<DataModule> fetchDataUserDetail(String userLogin, int userId);

	Modul findBymodulId(Integer modulId);
}
