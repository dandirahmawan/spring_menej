package com.menej.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.model.db.Invitation;
import com.menej.repo.InvitationRepo;

@Service
public class InvitationService {
	@Autowired
	InvitationRepo ir;
	
	public List<Invitation> getInvitationReady(String email, String status, int userId){
		return ir.findByInvitationEmailAndStatusAndUserId(email, status, userId);
	}

	public int cancelInvitaiton(int userId, String email){
//		int rtn = ir.deleteInvitationByUserIdAndInvitationEmail(userId, email);
		return 0;
	}
	
	public int insertInvitation(String email, String userId, String code, int projectId) {
		List<Invitation> list = new ArrayList<Invitation>();
		list = ir.findByInvitationEmailAndUserIdAndProjectId(email, Integer.parseInt(userId), projectId);
		if(list.size() > 0){
			for(int i = 0;i<list.size();i++) {
				ir.delete(list.get(i));
			}
		}
		
		Invitation invitation = new Invitation();
		invitation.setInvitationEmail(email);
		invitation.setUserId(Integer.parseInt(userId));
		invitation.setProjectId(projectId);
		invitation.setInvitationCode(code);
		invitation.setInvitationDate(new Date());
		ir.save(invitation);
		
		
		return 0;
	}
}
