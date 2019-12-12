package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Utils {
    public String RandomString(int length){
        int count = length;
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public Boolean getAccess(int userId, String sessionId){
        Boolean rtn = false;
        DBConnection gc = new DBConnection();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rst = null;

        String sql = "SELECT COUNT(user_id) count FROM user WHERE user_id = ? AND session_id = ?";

        con = gc.getConnection();
        try{
            pr = con.prepareStatement(sql);
            pr.setInt(1, userId);
            pr.setString(2, sessionId);
            rst = pr.executeQuery();
            if(rst.next()){
                if(rst.getInt("count") > 0){
                    rtn = true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       

        return rtn;
    }
}