package com.menej.service;

import com.menej.DBConnection;
import com.menej.Utils;
import com.menej.model.UserLogin;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    public List<Object> login(String email, String pass){
        DBConnection gc = new DBConnection();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rst = null;
//        List<UserLogin> list = null;
//        list = new ArrayList<UserLogin>();
        Map<String, Object> map = new HashMap<>();

//		System.out.println(pass);
        String sql = "SELECT user_id, session_id FROM user WHERE email_user = ? AND user_password = ?";
        con = gc.getConnection();
        try {
            pr = con.prepareStatement(sql);
            pr.setString(1, email);
            pr.setString(2, pass);
            rst = pr.executeQuery();
            if(rst.next()) {
//                UserLogin ul = new UserLogin();
//                list = new ArrayList<UserLogin>();

                if(rst.getString("session_id") == null || rst.getString("session_id").equals("")){
                    Utils util = new Utils();
                    String random = util.RandomString(20);
                    int userId = rst.getInt("user_id");
                    String sql1 = "UPDATE user SET session_id = ? WHERE user_id = ?";

                    pr.close();
                    pr = con.prepareStatement(sql1);
                    pr.setString(1, random);
                    pr.setInt(2, userId);
                    pr.executeUpdate();
                    map.put("sessionId", random);
                    map.put("userId", String.valueOf(userId));
//                    ul.setSessionId(random);
//                    ul.setUserId(String.valueOf(userId));
//                    list.add(ul);
                }else{
//                    ul.setSessionId(rst.getString("session_id"));
//                    ul.setUserId(rst.getString("user_id"));
                    map.put("sessionId", rst.getString("session_id"));
                    map.put("userId", rst.getString("user_id"));
                }
            }else{
                map.put("code", 201);
                map.put("message", "User not registered");
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

        List<Object> obj = new ArrayList<Object>();
        obj.add(map);
        return obj;
    }
}
