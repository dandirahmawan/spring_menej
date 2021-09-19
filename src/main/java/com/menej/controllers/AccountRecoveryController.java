package com.menej.controllers;

import com.menej.DBConnection;
import com.menej.SslEmail;
import com.menej.Utils;
import com.menej.model.db.ForgetPassword;
import com.menej.model.db.User;
import com.menej.repo.ForgetPasswordRepo;
import com.menej.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@RestController
public class AccountRecoveryController {
    @Autowired
    ForgetPasswordRepo fpr;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/submit_forget_password")
    public String submitForgetPassword(String r_code, String pass, String pass_confirm){
        String rtn = null;
        List<ForgetPassword> forgetPasswordList = fpr.findByCode(r_code);
        if(forgetPasswordList.size() > 0){
            DBConnection gc = new DBConnection();
            Connection conn = gc.getConnection();
            PreparedStatement pr = null;
            String sql = "UPDATE user SET user_password = ? WHERE user_id = ?";

            try{
                pr = conn.prepareStatement(sql);
                pr.setString(1, pass);
                pr.setInt(2, forgetPasswordList.get(0).getUserId());
                int exc = pr.executeUpdate();
                if(exc == 1) {
                    rtn = "success";
                    fpr.deleteForgetPasswordByUserId(forgetPasswordList.get(0).getUserId());
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if(conn != null) conn.close();
                    if(pr != null) pr.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return rtn;
    }

    @GetMapping("/recover_account")
    public String getUserRecovery(String r_code){
        System.out.println(r_code);
        DBConnection gc = new DBConnection();
        Connection conn = gc.getConnection();
        String sql = "SELECT * FROM forget_password WHERE code = ?";
        String rtn = null;
        try{
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, r_code);
            ResultSet rst = pr.executeQuery();
            if(rst.next()){
                rtn = "1";
            }

            rst.close();
            pr.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(conn != null) conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return rtn;
    }

    @PostMapping("/recover_account")
    public List<User> recoverAccount(String email){
        List<User> users = userRepo.findByEmailUser(email);
        Utils ut = new Utils();
        String codeRecover = ut.RandomString(15);
        if(users.size() > 0){
            DBConnection gc = new DBConnection();
            Connection conn = gc.getConnection();

            int userId = users.get(0).getUserId();
            String sql = "INSERT INTO forget_password (user_id, code)\n" +
                    "VALUES (?, ?)\n" +
                    "ON DUPLICATE KEY UPDATE\n" +
                    "   code = ? \n";

            try {
                PreparedStatement pr = conn.prepareStatement(sql);
                pr.setInt(1, userId);
                pr.setString(2, codeRecover);
                pr.setString(3, codeRecover);
                pr.executeUpdate();
                pr.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if (conn != null) conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            SslEmail sslEmail = new SslEmail();
            sslEmail.sendEmailForgotPassword(email, codeRecover);
        }
        return users;
    }
}
