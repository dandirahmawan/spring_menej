package com.menej.repo;

import com.menej.model.db.Labels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface LabelsRepo extends JpaRepository<Labels, Integer> {
    @Query("SELECT dt FROM Labels dt WHERE projectId = ?1 ORDER BY Date ASC")
    List<Labels> findByProjectId(int projectId);

    @Transactional
    @Modifying
    int deleteByProjectIdAndLabel(int projectId, String label);
}
