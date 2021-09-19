package com.menej.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.menej.SslEmail;
import com.menej.Utils;
import com.menej.model.db.ChangeEmailRequest;
import com.menej.model.view.ViewUserRelation;
import com.menej.repo.ChangeEmailRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.DBConnection;
import com.menej.model.db.User;
import com.menej.repo.UserRepo;
import com.menej.repo.ViewUserRelationRepo;

@Service
public class UserService {
	@Autowired
	UserRepo ur;

	@Autowired
	ChangeEmailRequestRepo cerr;
	
	public User getDataById(int userId) {
		return ur.findByUserId(userId);
	}
	
	public String register(String email, String name, String password, String code) {
		List<User> ul = ur.findByEmailUser(email);
		String ready = null;
		
		if(ul.size() > 0) {
			ready = "ready";
		}else {
			ready = "1";
			DBConnection gc = new DBConnection();
			Connection con = gc.getConnection();
			PreparedStatement pr = null;
			
			try {
				pr = con.prepareStatement("INSERT INTO user_dummy (user_email, user_name, password, confirmation_code, date)\r\n" + 
											"VALUES (?, ?, ?, ?, SYSDATE())\r\n" + 
											"ON DUPLICATE KEY\r\n" + 
											"UPDATE user_name = ?, password = ?, confirmation_code = ?, date = SYSDATE()");
				pr.setString(1, email);
				pr.setString(2, name);
				pr.setString(3, password);
				pr.setString(4, code);
				pr.setString(5, name);
				pr.setString(6, password);
				pr.setString(7, code);
				pr.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try{
					if(con != null) con.close();
					if(pr != null) pr.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return ready;
	}

	public int changeEmailService(int userId, String email, String pass){
		System.out.println("change email request....");
		SslEmail sslEmail = new SslEmail();

		Utils utils = new Utils();
		Boolean isValidPass = utils.loginUserId(userId, pass);

		if(isValidPass){
			User user = ur.findByUserId(userId);
			Utils ut = new Utils();
			String codeConfirm = ut.RandomString(15);

			/*chechk if email is available or not registered*/
			List<User> users = ur.findByEmailUser(email);
			if(users.size() > 0){
				return 2;
			}else{
				//insert data to chnage_email_request
				ChangeEmailRequest changeEmailRequest = new ChangeEmailRequest();
				changeEmailRequest.setCodeRequest(codeConfirm);
				changeEmailRequest.setEmailRequest(email);
				changeEmailRequest.setUserId(userId);
				cerr.save(changeEmailRequest);

				sslEmail.sendEmailChangeEmail(email, codeConfirm, user.getUserName());
				return 0;
			}
		}else{
			return 1;
		}
	}

	public String editUser(int userId, String userName){
		User user = ur.findByUserId(userId);
		user.setUserName(userName);
		ur.save(user);
		return "success";
	}

	public void confirmChangeEmail(int userId, String email){
		User user = ur.findByUserId(userId);
		user.setEmailUser(email);
		ur.save(user);

		cerr.deleteChangeEmailRequestByUserId(userId);
	}

	public int confirmRegistration(String email, String confirmCode) {
		DBConnection gc = new DBConnection();
		Connection con = gc.getConnection();
		PreparedStatement pr = null;
		
		int exc = 0;
		String sql = "INSERT INTO user (email_user, user_name, user_password, date)"
						+ "SELECT user_email, user_name, password, SYSDATE() FROM user_dummy WHERE user_email = ? AND confirmation_code = ?";
		
		String sql2 = "DELETE FROM user_dummy WHERE user_email = ?";
		//		System.out.println(sql);
		int count = validCode(email, confirmCode);
		if(count > 0) {
			try {
				pr = con.prepareStatement(sql);
				pr.setString(1, email);
				pr.setString(2, confirmCode);
				exc = pr.executeUpdate();
				pr.close();
				
				pr = con.prepareStatement(sql2);
				pr.setString(1, email);
				pr.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try{
					if(con != null) con.close();
					if(pr != null) pr.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return exc;
	}
	
	public int validCode(String email, String confirmCode) {
		DBConnection gc = new DBConnection();
		Connection con = gc.getConnection();
		PreparedStatement pr = null;
		ResultSet rst = null;
		int count = 0;
		String sql = "SELECT COUNT(*) as count_row FROM user_dummy WHERE user_email = ? AND confirmation_code = ?";
		try {
			pr = con.prepareStatement(sql);
			pr.setString(1, email);
			pr.setString(2, confirmCode);
			rst = pr.executeQuery();
			if(rst.next()) {
				count = rst.getInt("count_row");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
//	public void deleteUserDummy(String email) {
//		DBConnection gc = new DBConnection();
//		Connection con = gc.getConnection();
//		PreparedStatement pr = null;
//		try {
//			pr = con.prepareStatement("DELETE user_dummy WHERE user_email = ?");
//			pr.setString(1, email);
//			pr.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(con != null) con.close();
//				if(pr != null) pr.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
}	
