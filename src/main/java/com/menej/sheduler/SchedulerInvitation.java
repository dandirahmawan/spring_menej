package com.menej.sheduler;

import com.menej.model.view.ViewSchedulerInvitaton;
import com.menej.repo.InvitationRepo;
import com.menej.repo.ViewSchedulerInvitationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulerInvitation {

    @Autowired
    ViewSchedulerInvitationRepo vir;

    @Autowired
    InvitationRepo ir;

    @Scheduled(cron = "*/60 * * * * *")
    public void scheduleTaskUsingCronExpression() {

        /*shceduller for delete expired invitation*/
        List<ViewSchedulerInvitaton> viewSchedulerInvitatons = vir.findDataDeleted();
        for(int i = 0;i<viewSchedulerInvitatons.size();i++){
            int invId = viewSchedulerInvitatons.get(i).getInvitationId();
            ir.deleteInvitationByInvitationId(invId);
        }


    }
}
