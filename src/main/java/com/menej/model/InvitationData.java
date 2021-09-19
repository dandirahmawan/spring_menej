package com.menej.model;

import com.menej.model.view.DataProjectTeam;
import com.menej.model.view.ViewInvitation;

import java.util.List;
import java.util.Optional;

public class InvitationData {
    List<DataProjectTeam> dataProjectTeamList;
    Optional<ViewInvitation> viewInvitation;

    public InvitationData(List<DataProjectTeam> dataProjectTeamList, Optional<ViewInvitation> viewInvitation){
        this.viewInvitation = viewInvitation;
        this.dataProjectTeamList = dataProjectTeamList;
    }

    public List<DataProjectTeam> getDataProjectTeamList() {
        return dataProjectTeamList;
    }

    public void setDataProjectTeamList(List<DataProjectTeam> dataProjectTeamList) {
        this.dataProjectTeamList = dataProjectTeamList;
    }

    public Optional<ViewInvitation> getViewInvitation() {
        return viewInvitation;
    }

    public void setViewInvitation(Optional<ViewInvitation> viewInvitation) {
        this.viewInvitation = viewInvitation;
    }
}
