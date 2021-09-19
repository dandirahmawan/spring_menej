package com.menej.repo;

import com.menej.model.view.ViewTab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewTabRepo extends JpaRepository<ViewTab, Integer> {
    @Query("SELECT \n" +
            "vt \n" +
            "FROM ViewTab vt\n" +
            "WHERE ((vt.createdBy = ?1 OR vt.privacy IS NULL OR vt.privacy = 'pu') AND vt.tabId = ?2)\n" +
            " OR (vt.tabId = (SELECT up.tabId FROM UserPrivacyTab up WHERE up.userId = ?1 AND up.tabId = ?2))")
    ViewTab findByTabIdQuery(int userId, int tabId);
}
