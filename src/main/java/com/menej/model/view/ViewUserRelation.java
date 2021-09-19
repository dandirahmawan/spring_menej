package com.menej.model.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_view_user_relation")
public class ViewUserRelation{
    @Id
    @Column(name="user_id")
    private int userId;

    @Column(name="user_one")
    private int userOne;

    @Column(name="user_two")
    private int userTwo; 

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_user")
    private String emailUser;

    @Column(name = "pic_profile")
    private String picProfile;

    @Column(name = "pic_profile_detail")
    private String picProfileDetail;
    
    @Column(name = "relate_date")
    Date relateDate;

    @Column(name = "user_accessing")
    int userAccessing;

    @Column(name = "count_module")
    int countModule;

    @Column(name = "count_bugs")
    int countBugs;

    @Column(name = "count_doc_file")
    int countDocFile;

//    public ViewUserRelation(int user_id, int user_one, int user_two, String user_name, String email_user, String pic_profile, Date relateDate){
//        this.userId = user_id;
//        this.userOne = user_one;
//        this.userTwo = user_two;
//        this.userName = user_name;
//        this.emailUser = email_user;
//        this.picProfile = pic_profile;
//        this.relateDate = relateDate;
//    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPicProfile() {
        return picProfile;
    }

    public void setPicProfile(String picProfile) {
		this.picProfile = picProfile;
    }

    public int getUserOne() {
        return userOne;
    }

    public void setUserOne(int userOne) {
        this.userOne = userOne;
    }

    public int getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(int userTwo) {
        this.userTwo = userTwo;
    }

	public Date getRelateDate() {
		return relateDate;
	}

	public void setRelateDate(Date relateDate) {
		this.relateDate = relateDate;
	}

    public String getPicProfileDetail() {
        return picProfileDetail;
    }

    public void setPicProfileDetail(String picProfileDetail) {
        this.picProfileDetail = picProfileDetail;
    }

    public int getUserAccessing() {
        return userAccessing;
    }

    public void setUserAccessing(int userAccessing) {
        this.userAccessing = userAccessing;
    }

    public int getCountModule() {
        return countModule;
    }

    public void setCountModule(int countModule) {
        this.countModule = countModule;
    }

    public int getCountBugs() {
        return countBugs;
    }

    public void setCountBugs(int countBugs) {
        this.countBugs = countBugs;
    }

    public int getCountDocFile() {
        return countDocFile;
    }

    public void setCountDocFile(int countDocFile) {
        this.countDocFile = countDocFile;
    }
}