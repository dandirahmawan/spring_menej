package com.menej.repo;

import java.util.List;

import com.menej.model.view.DataProjectTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DataProjectTeamRepo extends JpaRepository<DataProjectTeam, String>{
    @Query("SELECT dt FROM DataProjectTeam dt WHERE dt.projectId = ?1 AND (dt.userStatus IS NULL OR dt.userStatus <> 'N')")
    List<DataProjectTeam> findByProjectIdQry(int projectId);
}