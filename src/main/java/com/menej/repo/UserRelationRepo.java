package com.menej.repo;

import java.util.List;

import com.menej.model.db.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRelationRepo extends JpaRepository<UserRelation, String>{
	@Query("SELECT data FROM UserRelation data WHERE (userOne = ?1 AND userTwo = ?2) OR (userOne = ?2 AND userTwo = ?1)")
	List<UserRelation> findToDelete(String user, String userLogin);
}
