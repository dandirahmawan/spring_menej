package com.menej.repo;

import java.util.List;

import com.menej.model.db.Permition;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface PermitionRepo extends JpaRepository<Permition, Integer>{
	List<Permition> findByProjectIdAndUserId(int projectId, int userId);

	@Transactional
	@Modifying
	Long deleteByProjectIdAndUserId(int projectId, int userId);
}
