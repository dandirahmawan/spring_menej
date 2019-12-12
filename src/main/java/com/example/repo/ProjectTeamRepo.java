package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.ProjectTeam;

public interface ProjectTeamRepo extends JpaRepository<ProjectTeam, String>{
	List<ProjectTeam> findAll();
}
