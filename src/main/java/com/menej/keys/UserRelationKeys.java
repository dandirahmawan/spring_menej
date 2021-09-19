package com.menej.keys;

import java.io.Serializable;

public class UserRelationKeys implements Serializable{
	String userOne;
	String userTwo;
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
}
