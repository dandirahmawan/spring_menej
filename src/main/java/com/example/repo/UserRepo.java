package com.example.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.User;

public interface UserRepo extends JpaRepository<User, String>{
	List<User> findByUserName(String name);
	User findByUserId(Integer userId);
	
	List<User> findByEmailUser(String email);
}
