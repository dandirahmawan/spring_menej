package com.menej.repo;

import com.menej.model.view.ViewStatusModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewStatusModuleRepo extends JpaRepository<ViewStatusModule, String> {
    List<ViewStatusModule> findByProjectId(int projectId);
    List<ViewStatusModule> findById(int statusId);
}
