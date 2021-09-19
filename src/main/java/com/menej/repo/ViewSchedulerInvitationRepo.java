package com.menej.repo;

import com.menej.model.view.ViewSchedulerInvitaton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewSchedulerInvitationRepo extends JpaRepository<ViewSchedulerInvitaton, Integer> {
    @Query("SELECT dt FROM ViewSchedulerInvitaton dt " +
            "WHERE (gap = 0 AND dateDifferent >= 3) OR \n" +
            "\t(dateDifferent > 0 AND gap > - 21)")
    List<ViewSchedulerInvitaton> findDataDeleted();
}
