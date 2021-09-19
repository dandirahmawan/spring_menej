package com.menej;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationUser {
    public int validUserLogin(String sessionId, int userId) {
        DBConnection gc = new DBConnection();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rst = null;
        String sql = "SELECT COUNT(user_id) count FROM user WHERE user_id = ? AND session_id = ?";
        int count = 0;
 
        try {
            con = gc.getConnection();
            pr = con.prepareStatement(sql);
            pr.setInt(1, userId);
            pr.setString(2, sessionId);
            rst = pr.executeQuery();
            if(rst.next()){
                count = rst.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	try {
        		if(con != null) con.close();
        		if(pr != null) pr.close();
        		if(rst != null) rst.close();
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	
        }
        return count;
    }
}