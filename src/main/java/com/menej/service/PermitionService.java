package com.menej.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.DBConnection;
import com.menej.model.db.Permition;
import com.menej.model.PermitionProject;
import com.menej.repo.PermitionRepo;

@Service
public class PermitionService {
	
	@Autowired
	PermitionRepo pr;
	
	public List<PermitionProject> getDataPermition(int userId, int projectId) {
		DBConnection gc = new DBConnection();
		Connection con = gc.getConnection();
		PreparedStatement pr = null;
		ResultSet rst = null;
		
		String sql = "SELECT * FROM(\r\n" + 
							"SELECT\r\n" + 
							"	pc.permition_code,\r\n" + 
							"	pc.permition_name,\r\n" + 
							"	pc.permition_description,\r\n" + 
							"	'Y' is_checked\r\n" + 
							"FROM\r\n" + 
							"	permition_code pc \r\n" + 
							"LEFT JOIN permition p ON pc.permition_code = p.permition_code\r\n" + 
							"WHERE p.user_id = ? AND p.project_id = ? \r\n" + 
							"UNION\r\n" + 
							"SELECT\r\n" + 
							"	pc.permition_code,\r\n" + 
							"	pc.permition_name,\r\n" + 
							"	pc.permition_description,\r\n" + 
							"	'N' is_checked\r\n" + 
							"FROM\r\n" + 
							"	permition_code pc\r\n" + 
							"WHERE pc.permition_code NOT IN (SELECT\r\n" + 
							"		pc.permition_code\r\n" + 
							"	FROM\r\n" + 
							"		permition_code pc \r\n" + 
							"	LEFT JOIN permition p ON pc.permition_code = p.permition_code\r\n" + 
							"	WHERE p.user_id = ? AND p.project_id = ?)\r\n" + 
						") R\r\n" + 
						"ORDER BY R.permition_code\r\n" + 
				"";
		
		List<PermitionProject> permitionProjectsList = new ArrayList<PermitionProject>();
		try {
			pr = con.prepareStatement(sql);
			pr.setInt(1, userId);
			pr.setInt(2, projectId);
			pr.setInt(3, userId);
			pr.setInt(4, projectId);
			rst = pr.executeQuery();
			
			while(rst.next()) {
				PermitionProject pp = new PermitionProject();
				Integer permitionCode 		= rst.getInt("permition_code");
				String permitionName 		= rst.getString("permition_name");
				String permitionDescription = rst.getString("permition_description");
				String isChecked 			= rst.getString("is_checked");
				
				pp.setPermitionCode(permitionCode);
				pp.setPermitionName(permitionName);
				pp.setPermitionDescription(permitionDescription);
				pp.setIsChecked(isChecked);
				permitionProjectsList.add(pp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(con != null) con.close();
				if(pr != null) pr.close();
				if(rst != null) rst.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return permitionProjectsList;
	}

	public Boolean setManagePermition(int projectId, String permition){
		try{
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(permition);
			JSONArray jsonArray = (JSONArray) json.get("permitionData");

			for(int i = 0;i<jsonArray.size();i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				long userIdData = (long) obj.get("userId");
				JSONArray permitionData = (JSONArray) obj.get("permition");

				//delete old permition data
				pr.deleteByProjectIdAndUserId(projectId, (int) userIdData);

				for(int x = 0;x<permitionData.size();x++){
					long permitionId = (long) permitionData.get(x);
					int permitionIdInt = (int) permitionId;

					//insert data permition
					insertPermition((int)userIdData, projectId, permitionIdInt);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	public String insertPermition(int userId, int projectId, int permitionCode) {
		Permition permition = new Permition();
		permition.setProjectId(projectId);
		permition.setUserId(userId);
		permition.setPermitionCode(permitionCode);
		pr.save(permition);
		return null;
	}
	
	public String deletePermitionByProjectId(int projectId, int userId) {
		List<Permition> permitions = pr.findByProjectIdAndUserId(projectId, userId);
		for(int i = 0;i<permitions.size();i++) {
			pr.delete(permitions.get(i));
		}
		return null;
	}
	
	public String listPermitionProfile(int userId) {
		DBConnection gc = new DBConnection();
		Connection con = gc.getConnection();
		PreparedStatement pr = null;
		ResultSet rst = null;
		
		String sql = "SELECT\r\n" + 
					"	a.project_id,\r\n" + 
					"	a.project_name,\r\n" +
					"	a.pic,\r\n"+
					"	(SELECT user_name FROM user WHERE user_id = a.pic) pic_name, \r\n" + 
					"	b.list_permition\r\n" + 
					"FROM\r\n" + 
					"	project a\r\n" + 
					"LEFT JOIN (\r\n" + 
					"		SELECT\r\n" + 
					"			project_id,\r\n" + 
					"			user_id,\r\n" + 
					"			GROUP_CONCAT(permition_code SEPARATOR ',') list_permition\r\n" + 
					"		FROM permition \r\n" + 
					"		WHERE user_id = ?\r\n" + 
					"		GROUP BY project_id, user_id) b\r\n" + 
					"ON a.project_id = b.project_id\r\n" + 
					"LEFT JOIN project_team pt ON pt.project_id = a.project_id \r\n" + 
					"WHERE \r\n" + 
					"	(pt.user_id = ? OR a.pic = ?) AND\r\n" + 
					"	(a.is_delete != 'Y' OR a.is_delete IS NULL)\r\n" + 
					"GROUP BY a.project_id";
		
		String sql2 = "SELECT * FROM permition_code";
		
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArray2 = new JSONArray();
		JSONArray jsonArray3 = new JSONArray();
		
		//get array for permition project
		try {
			pr = con.prepareStatement(sql);
			pr.setInt(1, userId);
			pr.setInt(2, userId);
			pr.setInt(3, userId);
			rst = pr.executeQuery();
			
			
			while(rst.next()) {
				JSONObject jsonObject = new JSONObject();
				String permitionList = (rst.getString("list_permition") != null) ? rst.getString("list_permition") : "";
				String[] array = permitionList.split(",");
				
				JSONArray jsonArray4 = new JSONArray();
				for(int i = 0;i<array.length;i++) {
					int pCode = (array[i] != null && array[i] != "") ? Integer.parseInt(array[i]) : 0;
					if(pCode > 0) jsonArray4.add(pCode);
				}
				
				jsonObject.put("projectId", rst.getString("project_id"));
				jsonObject.put("projectName", rst.getString("project_name"));
				jsonObject.put("pic", rst.getString("pic"));
				jsonObject.put("picName", rst.getString("pic_name"));
				jsonObject.put("listPermition", jsonArray4);
				jsonArray.add(jsonObject);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//get array for permition type
		try {
			pr = con.prepareStatement(sql2);
			rst = pr.executeQuery();
			while(rst.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("permitionCode", rst.getInt("permition_code"));
				jsonObject.put("permitionName", rst.getString("permition_name"));
				jsonObject.put("permitionDescription", rst.getString("permition_description"));
				jsonArray2.add(jsonObject);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObjectAll = new JSONObject();
		jsonObjectAll.put("permitionProfile", jsonArray);
		jsonObjectAll.put("permitionCode", jsonArray2);
		jsonArray3.add(jsonObjectAll);
		return jsonArray3.toString();
	}
	
	public void deleteInvitation(String invitationEmail, String invitationCode) {
		String sql = "DELETE FROM invitation WHERE invitation_email = ? AND invitation_code = ?";
		DBConnection gc = new DBConnection();
		Connection con = gc.getConnection();
		PreparedStatement pr = null;
		
		try {
			pr = con.prepareStatement(sql);
			pr.setString(1, invitationEmail);
			pr.setString(2, invitationCode);
			pr.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
