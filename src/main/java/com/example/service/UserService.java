package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.model.ViewUserRelation;
import com.example.repo.UserRepo;
import com.example.repo.ViewUserRelationRepo;

@Service
public class UserService {
	@Autowired
	UserRepo ur;

	@Autowired
	ViewUserRelationRepo vurp;
	
	public List<User> getData() {
		List<User> us = ur.findAll();
		return us;
	}
	
	public List<User> getDataName(String name) {
		List<User> us = ur.findByUserName(name);
		return us;
	}

	public List<ViewUserRelation> getUserRelation(int userId){
		List<ViewUserRelation> vur = vurp.findUserRelation(userId, userId, userId);
		return vur;
	}
}
