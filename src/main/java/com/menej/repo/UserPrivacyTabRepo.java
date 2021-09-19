package com.menej.repo;

import com.menej.model.db.UserPrivacyTab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPrivacyTabRepo extends JpaRepository<UserPrivacyTab, Integer> {
    //empty code
    List<UserPrivacyTab> findByTabId(int tabId);
}
