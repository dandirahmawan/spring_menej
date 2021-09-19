package com.menej.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_name")
	private String userName;	
	
	@Column(name = "pic_profile")
	private String picProfile;

	@Column(name = "pic_profile_detail")
	private String picProfileDetail;
	
	@Column(name = "email_user")
	private String emailUser;

	public Integer getUserId() {
		return userId;
	}

	public void setUser_id(Integer userId) {
		this.userId = userId;
	} 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicProfileDetail() {
		return picProfileDetail;
	}

	public void setPicProfileDetail(String picProfileDetail) {
		this.picProfileDetail = picProfileDetail;
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
	
}
