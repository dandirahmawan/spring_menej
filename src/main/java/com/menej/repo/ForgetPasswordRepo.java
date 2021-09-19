package com.menej.repo;

import com.menej.model.db.ForgetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ForgetPasswordRepo extends JpaRepository<ForgetPassword, Integer> {
    List<ForgetPassword> findByCode(String code);

    String sql = "DELETE ForgetPassword WHERE userId = ?1";
    @Transactional
    @Modifying
    @Query(sql)
    int deleteForgetPasswordByUserId(int userId);
}
