package com.menej.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permition_code")
public class PermitionCode {
	
	@Id
	@Column(name = "permition_code")
	int permitionCode;
	
	@Column(name = "permition_name")
	String permitionName;
	
	@Column(name = "permition_description")
	String permitionDescription;

	public int getPermitionCode() {
		return permitionCode;
	}

	public void setPermitionCode(int permitionCode) {
		this.permitionCode = permitionCode;
	}

	public String getPermitionName() {
		return permitionName;
	} 

	public void setPermitionName(String permitionName) {
		this.permitionName = permitionName;
	}

	public String getPermitionDescription() {
		return permitionDescription;
	}

	public void setPermitionDescription(String permitionDescription) {
		this.permitionDescription = permitionDescription;
	}
	
}
