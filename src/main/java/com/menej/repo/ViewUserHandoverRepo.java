package com.menej.repo;

import com.menej.model.view.ViewUserHandover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewUserHandoverRepo extends JpaRepository<ViewUserHandover, Integer> {
    @Query("SELECT " +
            "dt FROM ViewUserHandover dt \n" +
            "WHERE dt.projectId = ?1 \n" +
            "GROUP BY dt.userId, dt.projectId")
    List<ViewUserHandover> findByProjectIdQry(int projectId);
}
