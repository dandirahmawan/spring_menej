package com.menej.repo;

import com.menej.model.db.StatusModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StatusModuleRepo extends JpaRepository<StatusModule, Integer> {
    List<StatusModule> findByProjectIdAndId(int projectId, int status);
    List<StatusModule> findByProjectIdAndStatus(int projectId, String statusName);

    @Transactional
    @Modifying
    @Query("DELETE FROM StatusModule WHERE projectId = ?1 AND id = ?2")
    int deleteStatus(int projectId, int statusId);
}
