package com.example.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DBConnection;
import com.example.Utils;
import com.example.model.UserLogin;

@RestController
public class LoginController {
	@PostMapping("/login")
	public List<UserLogin> login(@RequestParam String email, String password) {
		DBConnection gc = new DBConnection();
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rst = null;
		List<UserLogin> list = null;
		list = new ArrayList<UserLogin>();
		
		String sql = "SELECT user_id, session_id FROM user WHERE email_user = ? AND user_password = ?";
		con = gc.getConnection();
		try {
			pr = con.prepareStatement(sql);
			pr.setString(1, email);
			pr.setString(2, password);
			rst = pr.executeQuery();
			if(rst.next()) {
				UserLogin ul = new UserLogin();
				list = new ArrayList<UserLogin>();
				
				if(rst.getString("session_id") == null || rst.getString("session_id").equals("")){
					Utils util = new Utils();
					String random = util.RandomString(20);
					String sql1 = "UPDATE user SET session_id = ? WHERE user_id = ?";
					
					pr.close();
					pr = con.prepareStatement(sql1);
					pr.setString(1, random);
					pr.setInt(2, rst.getInt("user_id"));
					pr.executeUpdate();
					ul.setSessionId(random);
					ul.setUserId(rst.getString("user_id"));
					list.add(ul);
				}else{
					ul.setSessionId(rst.getString("session_id"));
					ul.setUserId(rst.getString("user_id"));
					list.add(ul);
				}
			}
			pr.close();
			rst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(con != null) con.close();
				if(rst != null) rst.close();
				if(pr != null) pr.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}

	// public String randomStringSession(){
	// 	String gen = random;
	// 	System.out.println(gen);
	// 	return null;
	// }
	
}
