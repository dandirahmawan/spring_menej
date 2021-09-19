package com.menej.repo;

import java.util.List;

import com.menej.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String>{
	List<User> findByUserName(String name);
	User findByUserId(Integer userId);
	
	List<User> findByEmailUser(String email);
}
