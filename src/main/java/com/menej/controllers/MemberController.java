package com.menej.controllers;

import java.util.ArrayList;
import java.util.List;

import com.menej.model.view.DataProjectTeam;
import com.menej.model.view.ViewUserRelationMember;
import com.menej.repo.ViewUserRelationMemberRepo;
import com.menej.service.PermitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.menej.Utils;
import com.menej.service.ProjectTeamService;
import com.menej.service.UserService;

@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired
	UserService us;
	
	@Autowired
	ProjectTeamService pts;

	@Autowired
	ViewUserRelationMemberRepo viewUserRelationMemberRepo;

	@Autowired
    PermitionService ps;
	
	@PostMapping("/manage_member")
	public List<ViewUserRelationMember> getListUserManage(String userId, String projectId){
		List<ViewUserRelationMember> viewUserRelationMembers = new ArrayList<ViewUserRelationMember>();
		viewUserRelationMembers =
				viewUserRelationMemberRepo.findByUserAccessingAndProjectIdQuery(Integer.parseInt(userId), Integer.parseInt(projectId));

		return viewUserRelationMembers;
	}
	
//	@PostMapping("/commit_manage_member")
//	public List<DataProjectTeam> addMember(String userIdList, String sessionId, String userId, String projectId, String permition) {
//		int userIdInt = Integer.parseInt(userId);
//		int projectIdInt = Integer.parseInt(projectId);
//
//		Utils utils = new Utils();
//		Boolean isAccess = utils.getAccess(userIdInt, sessionId);
//
//		if(isAccess) {
//            ps.setManagePermition(projectIdInt, permition);
//            pts.delete(Integer.parseInt(projectId));
//
//			String[] userIdL = userIdList.split(",");
//			for(int i = 0;i<userIdL.length;i++) {
//				pts.insertData(projectIdInt, Integer.parseInt(userIdL[i]));
//			}
//		}
//
//		return pts.getDataProjectTeam(projectIdInt);
//	}
	
	@PostMapping("/delete_member")
	public String delete(String userDelete, String projectId) {
		int exc = pts.delete(Integer.parseInt(userDelete), Integer.parseInt(projectId));
		if(exc == 1){
			return "success";
		}else{
			return "failed";
		}
	}

	@GetMapping("/list/{projectId}")
	public List<DataProjectTeam> getMember(@PathVariable String projectId){
		int projectIdInt = Integer.parseInt(projectId);
		return pts.getDataProjectTeam(projectIdInt);
	}
}
