package com.menej.repo;

import com.menej.model.view.ViewLabelModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewLabelModuleRepo extends JpaRepository<ViewLabelModule, String> {
    List<ViewLabelModule> findByProjectId(int projectId);
    List<ViewLabelModule> findByModuleId(int moduleId);
}
