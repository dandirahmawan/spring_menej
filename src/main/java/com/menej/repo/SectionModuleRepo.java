package com.menej.repo;

import com.menej.model.db.SectionModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionModuleRepo extends JpaRepository<SectionModule, Integer> {
    List<SectionModule> findByProjectId(Integer projectId);
}
