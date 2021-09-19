package com.menej.repo;

import com.menej.model.view.ViewManageMemberProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewManageMemberProjectRepo extends JpaRepository<ViewManageMemberProject, Integer> {
    @Query("SELECT dt FROM " +
            "ViewManageMemberProject dt " +
            "WHERE projectId = ?1 " +
            "AND (userAccessing = ?2 OR userAccessing = 0) " +
            "AND userId <> ?2 AND pic = ?2 " +
            "GROUP BY userId")
    List<ViewManageMemberProject> findByProjectIdAndUserAccessing(int projectId, int userId);

    @Query("SELECT dt FROM " +
            "ViewManageMemberProject dt " +
            "WHERE (userAccessing = ?1 OR userAccessing = 0) " +
            "AND userId <> ?1 AND pic = ?1 " +
            "GROUP BY userId")
    List<ViewManageMemberProject> findByUserIdQuery(int userId);
}
