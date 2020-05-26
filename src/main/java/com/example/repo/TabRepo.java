package com.example.repo;

import com.example.model.DataTab;
import com.example.model.Tab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TabRepo extends JpaRepository<Tab, Integer> {
    String sql = "SELECT new com.example.model.DataTab (\n" +
                        "t.tabId, \n" +
                        "t.projectId, \n" +
                        "t.tabName, \n" +
                        "t.createdBy, \n" +
                        "t.fileFrm, \n" +
                        "t.privacy, \n" +
                        "t.isDelete, \n" +
                        "usr.userName, \n" +
                        "usr.userId \n" +
                    ") FROM Tab t \n" +
                    "JOIN Project p ON p.projectId = t.projectId \n" +
                    "JOIN User usr ON usr.userId = t.createdBy \n" +
                    "LEFT JOIN UserPrivacy userPrivacy ON userPrivacy.tabId = t.tabId \n"+
                    "WHERE \n" +
                    "(t.projectId = ?1 AND t.privacy = 'pu' AND (t.isDelete IS NULL OR t.isDelete != 'Y')) \n" +
                    "OR ((t.createdBy = ?2 OR userPrivacy.userId = ?2) AND t.projectId = ?1 AND (t.isDelete IS NULL OR t.isDelete != 'Y')) \n" +
                    "OR (t.projectId = ?1 AND p.pic = ?2 AND (t.isDelete IS NULL OR t.isDelete != 'Y')) \n"+
                    "ORDER BY t.tabName ASC";
    @Query(sql)
    List<DataTab> findByProjectIdQuery(int projectId, int userId);

    String sql2 = "SELECT t FROM Tab t " +
                "WHERE t.projectId = ?1 " +
                "   AND t.tabName = ?2 " +
                "   AND t.createdBy = ?3 " +
                "   AND (t.isDelete IS NULL OR t.isDelete != 'Y')";
    @Query(sql2)
    List<Tab> findByProjectIdAndTabNameAndUserIdAndIsDeleteNotQuery(int projectId, String tabName, int userId);
    List<Tab> findByTabId(int tabId);
    List<Tab> findByTabIdAndTabName(int tabId, String tabName);
}
