package com.menej.repo;

import com.menej.model.db.LabelModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface LabelModuleRepo extends JpaRepository<LabelModule, String> {
    List<LabelModule> findByProjectId(int projectId);

    @Transactional
    @Modifying
    int deleteByModuleId(int moduleId);

    @Transactional
    @Modifying
    int deleteByProjectIdAndLabel(int projectId, String label);
}
