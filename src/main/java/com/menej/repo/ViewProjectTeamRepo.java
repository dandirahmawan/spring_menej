package com.menej.repo;

import com.menej.model.view.ViewProjectTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewProjectTeamRepo extends JpaRepository<ViewProjectTeam, Integer> {
    List<ViewProjectTeam> findByProjectId(int projectId);
}
