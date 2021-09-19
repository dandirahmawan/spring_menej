package com.menej.repo;

import com.menej.model.db.ChangeEmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface ChangeEmailRequestRepo extends JpaRepository<ChangeEmailRequest, Integer> {
    List<ChangeEmailRequest> findByCodeRequestAndUserId(String codeRequest, int userId);

    @Transactional
    @Modifying
    int deleteChangeEmailRequestByUserId(int userId);
}
