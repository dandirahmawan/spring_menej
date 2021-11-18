package com.menej.service;

import com.menej.DBConnection;
import com.menej.JwtTokenUtil;
import com.menej.Utils;
import com.menej.model.UserLogin;
import com.menej.model.db.User;
import com.menej.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    DBConnection gc;

    @Autowired
    UserRepo ur;

    @Autowired
    JwtTokenUtil jwt;

    public List<Object> login(String email, String pass){
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rst = null;
        Map<String, Object> map = new HashMap<>();
        int userId = 0;

        String sql = "SELECT user_id, session_id FROM user WHERE email_user = ? AND user_password = ?";
        con = gc.getConnection();
        try {
            pr = con.prepareStatement(sql);
            pr.setString(1, email);
            pr.setString(2, pass);
            rst = pr.executeQuery();
            if(rst.next()) {
                userId = rst.getInt("user_id");
                if(
                        rst.getString("session_id") == null ||
                        rst.getString("session_id").equals(""))
                {
                    Utils util = new Utils();
                    String random = util.RandomString(20);
                    String sql1 = "UPDATE user SET session_id = ? WHERE user_id = ?";

                    pr.close();
                    pr = con.prepareStatement(sql1);
                    pr.setString(1, random);
                    pr.setInt(2, userId);
                    pr.executeUpdate();
                    map.put("sessionId", random);
                    map.put("userId", String.valueOf(userId));
                }else{
                    map.put("code", 200);
                    map.put("sessionId", rst.getString("session_id"));
                    map.put("userId", rst.getString("user_id"));
                }

                /*set token jwt*/
                User user = ur.findByUserId(userId);
                String token = jwt.generateToken(user);
                map.put("token", token);
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
