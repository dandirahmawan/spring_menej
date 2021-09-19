package com.menej.repo;

import com.menej.model.db.AssignedModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface AssignedModuleRepo extends JpaRepository<AssignedModule, Integer> {
//    List<AssignedModule> findByProjectId(int projectId);
//    List<AssignedModule> findByModuleId(int moduleId);

    @Transactional
    @Modifying
    int deleteByModuleId(int moduleId);
}
