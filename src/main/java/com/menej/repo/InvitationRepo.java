package com.menej.repo;

import java.util.List;

import com.menej.model.db.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface InvitationRepo extends JpaRepository<Invitation, Integer>{
	List<Invitation> findByInvitationEmailAndStatusAndUserId(String email, String status, int userId);
	List<Invitation> findByInvitationEmailAndUserIdAndProjectId(String email, int userId, int projectId);

	@Transactional
	@Modifying
	int deleteInvitationByInvitationId(int invitationId);

	@Transactional
	@Modifying
	int deleteInvitationByUserIdAndInvitationEmailAndInvitationCode(int userId, String email, String invitationCode);
}
