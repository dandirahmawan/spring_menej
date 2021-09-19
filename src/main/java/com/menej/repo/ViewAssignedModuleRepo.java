package com.menej.repo;

import com.menej.model.view.ViewAssignedModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewAssignedModuleRepo extends JpaRepository<ViewAssignedModule, Integer> {
    List<ViewAssignedModule> findByProjectId(int projectId);
    List<ViewAssignedModule> findByModuleId(int moduleId);
}
