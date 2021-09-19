package com.menej.controllers;

import com.menej.DBConnection;
import com.menej.SslEmail;
import com.menej.Utils;
import com.menej.model.InvitationData;
import com.menej.model.db.ProjectTeam;
import com.menej.model.db.User;
import com.menej.model.db.UserRelation;
import com.menej.model.view.DataProjectTeam;
import com.menej.model.view.ViewInvitation;
import com.menej.model.view.ViewManageMemberProject;
import com.menej.repo.*;
import com.menej.service.InvitationService;
import com.menej.service.PermitionService;
import com.menej.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class InvitationController {

    @Autowired
    UserRelationRepo urr;

    @Autowired
    UserService us;

    @Autowired
    PermitionService ps;

    @Autowired
    InvitationService is;

    @Autowired
    UserRepo ur;

    @Autowired
    InvitationRepo ir;

    @Autowired
    ViewInvitationRepo vir;

    @Autowired
    ProjectTeamRepo ptr;

    @Autowired
    DataProjectTeamRepo dptr;

    @PostMapping("/invitation")
    public String invitingUser(String email, String userId, String projectId){
        int projectIdInt = (projectId.equals("") || projectId == null) ? 0 : Integer.parseInt(projectId);
        String[] em = email.split(" ");
        for(String item : em){
            Utils ut = new Utils();
            String codeConfirm = ut.RandomString(15);
            SslEmail se = new SslEmail();

            User user = ur.findByUserId(Integer.parseInt(userId));
            se.sendEmail(item, codeConfirm, user.getUserName());

            List<com.menej.model.db.Invitation> invitations = new ArrayList<com.menej.model.db.Invitation>();
            invitations = is.getInvitationReady(email, "OK", Integer.parseInt(userId));
            if(invitations.size() < 1) {
                is.insertInvitation(email, userId, codeConfirm, projectIdInt);
            }else {

            }
        }

        return "success";
    }

    @PostMapping("/cancel_invitation")
    public String cancelInvitingUser(String email, String userId){
        int userIdInt = (userId.equals("") || userId == null) ? 0 : Integer.parseInt(userId);
        is.cancelInvitaiton(userIdInt, email);
        return "success";
    }


    @PostMapping("/conf_invitation")
    public InvitationData confInvitation(String sessionId, int userId, String conf) {
        User user = us.getDataById(userId);
        String emailUser = user.getEmailUser();

        Optional<ViewInvitation> viewInvitation =
                vir.findByInvitationCodeAndInvitationEmail(conf, emailUser);

        int projectId = viewInvitation.map(ViewInvitation::getProjectId).orElse(0);
        List<DataProjectTeam> dataProjectTeams =
                dptr.findByProjectIdQry(projectId);

        InvitationData invitationData = new InvitationData(dataProjectTeams, viewInvitation);
        return invitationData;
    }

    @PostMapping("/accept_invitation")
    public String acc(String conf, int userId, int projectId){
        User user = ur.findByUserId(userId);
        String email = user.getEmailUser();


        String result = "";
        Optional<ViewInvitation> viewInvitation = vir.findByInvitationCodeAndInvitationEmail(conf, email);
        int invitationId = viewInvitation.map(ViewInvitation::getInvitationId).orElse(0);
        int userIdInv = viewInvitation.map(ViewInvitation::getUserId).orElse(0);
        if(invitationId > 0){
            Date date = new Date();
            ProjectTeam projectTeam = new ProjectTeam();
            projectTeam.setProjectId(projectId);
            projectTeam.setUserId(userId);
            projectTeam.setCreatedDate(date);
            ptr.save(projectTeam);
            ir.deleteInvitationByUserIdAndInvitationEmailAndInvitationCode(userIdInv, email, conf);
            result = "success";
        }else{
            result = "not found";
        }

        return result;
    }
}
