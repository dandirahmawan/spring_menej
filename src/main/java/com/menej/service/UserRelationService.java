package com.menej.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.DBConnection;
import com.menej.model.db.UserRelation;
import com.menej.repo.ProjectTeamRepo;
import com.menej.repo.UserRelationRepo;

@Service
public class UserRelationService {
	@Autowired
	UserRelationRepo urr;
	
	@Autowired
	ProjectTeamRepo ptr;
	
	public String deleteUserRelation(int user, int userLogin) {
		
		List<UserRelation> userRelations = urr.findToDelete(String.valueOf(user), String.valueOf(userLogin));
		for(int i = 0;i<userRelations.size();i++) {
			int del = deleteTeam(String.valueOf(userLogin), user);
			if(del != 0) {
			    urr.delete(userRelations.get(i));
			}
		}
		return null;
	}
	
	public int deleteTeam(String pic, int userId){
		DBConnection gc = new DBConnection();
		Connection con = gc.getConnection();
		PreparedStatement pr = null;
		
		int delete = 0;
		String sql = "UPDATE \r\n" +
						"	project_team \r\n" +
                        "SET user_status = 'N' \n"+
						"WHERE \r\n" +
						"	project_id IN (\r\n" + 
						"		SELECT\r\n" + 
						"			pt.project_id\r\n" + 
						"		FROM\r\n" + 
						"			(SELECT * FROM project_team) pt\r\n" + 
						"		JOIN project ON project.project_id = pt.project_id\r\n" + 
						"		WHERE\r\n" + 
						"			project.pic = ? AND project_team.user_id = ?\r\n" + 
						"	)\r\n" + 
						"AND project_team.user_id = ?";
		
		try {
			pr = con.prepareStatement(sql);
			pr.setString(1, pic);
			pr.setInt(2, userId);
			pr.setInt(3, userId);
			delete = pr.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pr != null) pr.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return delete;
	}
}
