package com.menej.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.menej.keys.UserRelationKeys;

@Entity
@Table(name = "user_relation")
@IdClass(UserRelationKeys.class)
public class UserRelation {
	@Id
	@Column(name = "user_one")
	String userOne;
	
	@Id
	@Column(name = "user_two")
	String userTwo;
	
	@Column(name = "relate_date")
	Date relateDate;

	public String getUserOne() {
		return userOne;
	}

	public void setUserOne(String userOne) {
		this.userOne = userOne;
	} 

	public String getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(String userTwo) {
		this.userTwo = userTwo;
	}

	public Date getRelateDate() {
		return relateDate;
	}

	public void setRelateDate(Date relateDate) {
		this.relateDate = relateDate;
	}
	
}
