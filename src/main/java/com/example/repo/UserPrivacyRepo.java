package com.example.repo;

import com.example.model.UserPrivacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPrivacyRepo extends JpaRepository<UserPrivacy, Integer> {
    //empty code
    List<UserPrivacy> findByTabId(int tabId);
}
