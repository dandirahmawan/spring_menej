package com.menej.model;

import com.menej.model.db.User;

import java.util.List;

public class PermitionPage {
	User dataUser;
	List<PermitionProject> dataPermition;
	
	public User getDataUser() {
		return dataUser;
	}
	public void setDataUser(User dataUser) {
		this.dataUser = dataUser;
	}
	public List<PermitionProject> getDataPermition() {
		return dataPermition;
	}
	public void setDataPermition(List<PermitionProject> dataPermition) {
		this.dataPermition = dataPermition;
	}
	
} 
