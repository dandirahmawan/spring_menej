package com.example.repo;

import java.util.List;

import com.example.model.DataProjectTeam;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataProjectTeamRepo extends JpaRepository<DataProjectTeam, String>{
    List<DataProjectTeam> findByProjectId(int projectId);
}