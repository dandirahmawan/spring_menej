package com.menej.repo;

import com.menej.model.view.ViewUserRelationMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewUserRelationMemberRepo extends JpaRepository<ViewUserRelationMember, Integer> {
    String sql = "SELECT\n" +
                "   data.user_id,\n" +
                "   data.user_name,\n" +
                "   data.email_user,\n" +
                "   data.pic_profile,\n" +
                "   data.pic_profile_detail,\n" +
                "   data.relate_date,\n" +
                "   data.project_id,\n" +
                "   data.is_related,\n" +
                "   data.is_invited,\n" +
                "   data.user_accessing,\n" +
                "   CASE\n" +
                "       WHEN pt.project_id IS NULL THEN \"N\"\n" +
                "       WHEN data.is_member = \"Y\" THEN \"Y\"\n" +
                "       ELSE \"Y\"\n" +
                "   END is_member\n" +
                "FROM view_user_relation_member data\n" +
                "LEFT JOIN (SELECT dt.* FROM project_team dt WHERE dt.project_id = ?2) pt ON pt.user_id = data.user_id \n" +
                "WHERE (data.project_id = ?2 OR data.project_id = 0) AND \n" +
                        "       data.user_id != ?1 AND \n" +
                        "       data.user_accessing = ?1 \n" +
                        "       GROUP BY data.user_id";
    @Query(value = sql, nativeQuery = true)
    List<ViewUserRelationMember> findByUserAccessingAndProjectIdQuery(int userId, int projectId);

    String sql2 = "SELECT\n" +
            "   data.user_id,\n" +
            "   data.user_name,\n" +
            "   data.email_user,\n" +
            "   data.pic_profile,\n" +
            "   data.pic_profile_detail,\n" +
            "   data.relate_date,\n" +
            "   data.project_id,\n" +
            "   data.is_related,\n" +
            "   data.is_invited,\n" +
            "   data.user_accessing,\n" +
            "   '' is_member \n" +
            "FROM view_user_relation_member data\n" +
            "WHERE data.user_id != ?1 AND \n" +
            "       data.user_accessing = ?1 \n" +
            "       GROUP BY data.user_id";
    @Query(value = sql2, nativeQuery = true)
    List<ViewUserRelationMember> findByUserAccessingQuery(int userId);
}
