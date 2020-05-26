package com.example.repo;

import java.util.List;

import com.example.model.DataProjectTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DataProjectTeamRepo extends JpaRepository<DataProjectTeam, String>{
    @Query("SELECT dt FROM DataProjectTeam dt WHERE dt.projectId = ?1 AND (dt.userStatus IS NULL OR dt.userStatus = 'Y')")
    List<DataProjectTeam> findByProjectIdQry(int projectId);
}