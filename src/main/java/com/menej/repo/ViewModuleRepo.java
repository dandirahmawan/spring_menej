package com.menej.repo;

import com.menej.model.view.ViewModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.View;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ViewModuleRepo extends JpaRepository<ViewModule, Integer>{
    @Query("SELECT dt FROM ViewModule dt WHERE dt.createdBy = ?1 AND dt.createdDate = ?2 AND dt.modulName = ?3 AND dt.projectId = ?4 GROUP BY dt.modulId")
    Optional<ViewModule> fetchLast(int userLogin, Date date, String moduleName, int projectId);

    ViewModule findByModulId(int moduleId);
    List<ViewModule> findBySectionId(int id);
}
