package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	private Integer user_id;
	
	@Column(name = "user_name")
	private String userName;	
	
	@Column(name = "pic_profile")
	private String picProfile;
	
	@Column(name = "email_user")
	private String emailUser;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicProfile() {
		return picProfile;
	}

	public void setPicProfile(String picProfile) {
		this.picProfile = picProfile;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	// @OneToMany(mappedBy = "user")
	// private List<Modul> modul;
	
	
	
}
