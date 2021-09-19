package com.menej.model;

import java.util.Date;

public class BugsUserDetail {
    String note;
    Date createDate;

    public BugsUserDetail(String note, Date date){
        this.note = note;
        this.createDate = date;
    }

    public String getNote() {
        return note;
    } 

    public void setNote(String note) {
        this.note = note;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

    
}