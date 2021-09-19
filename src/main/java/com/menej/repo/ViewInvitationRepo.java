package com.menej.repo;

import com.menej.model.view.ViewInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViewInvitationRepo extends JpaRepository<ViewInvitation, Integer> {
    Optional<ViewInvitation> findByInvitationCodeAndInvitationEmail(String invitationId, String email);
}
