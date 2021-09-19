package com.menej.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menej.model.PermitionPage;
import com.menej.service.PermitionService;
import com.menej.service.UserService;

@RestController
public class PermitionController {
	
	@Autowired
	PermitionService ps;
	
	@Autowired
	UserService us;
	
	@PostMapping("/permition_project")
	public PermitionPage getPermition(String userId, String projectId) {
		int userIdInt = Integer.parseInt(userId);
		int projectIdInt = Integer.parseInt(projectId);
		PermitionPage permitionPage = new PermitionPage(); 
		permitionPage.setDataPermition(ps.getDataPermition(userIdInt, projectIdInt));
		permitionPage.setDataUser(us.getDataById(userIdInt));
		return permitionPage;  
	}
	
	@PostMapping("/set_permition")
	public String setPermition(int user_id, int project_id, String permition_code) {
		String[] permitionCode = permition_code.split(",");
		ps.deletePermitionByProjectId(project_id, user_id);
		for(String item : permitionCode) {
			if(!item.equals("")) {
				int pcInt = Integer.parseInt(item);
				ps.insertPermition(user_id, project_id, pcInt);
			}
		}
		return "success";
	}
	
	@PostMapping("list_permition_profile")
	public String getListPermitionProfile(String userId) {
		return ps.listPermitionProfile(Integer.parseInt(userId));
	}
}
