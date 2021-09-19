package com.menej.repo;

import com.menej.model.db.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepo extends JpaRepository<Section, Integer> {
    List<Section> findByProjectId(int projectId);
}
