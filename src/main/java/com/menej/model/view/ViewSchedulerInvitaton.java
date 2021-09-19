package com.menej.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "view_scheduler_invitation")
public class ViewSchedulerInvitaton {
    @Id
    @Column(name = "invitation_id")
    int invitationId;

    @Column(name = "invitation_date")
    Date invitationDate;

    @Column(name = "curr_date")
    Date currDate;

    @Column(name = "date_different")
    int dateDifferent;

    @Column(name = "gap")
    Long gap;

    public int getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(int invitationId) {
        this.invitationId = invitationId;
    }

    public Date getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(Date invitationDate) {
        this.invitationDate = invitationDate;
    }

    public Date getCurrDate() {
        return currDate;
    }

    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }

    public int getDateDifferent() {
        return dateDifferent;
    }

    public void setDateDifferent(int dateDifferent) {
        this.dateDifferent = dateDifferent;
    }

    public Long getGap() {
        return gap;
    }

    public void setGap(Long gap) {
        this.gap = gap;
    }
}
