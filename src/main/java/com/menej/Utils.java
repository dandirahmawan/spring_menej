package com.menej;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class Utils {

    DBConnection gc = new DBConnection();

    public String RandomString(int length){
        int count = length;
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder builder = new StringBuilder();
        for (int i = 0;i<count;i++) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    } 
    
    public String RandomNumber(int length){
        int count = length;
        String ALPHA_NUMERIC_STRING = "0123456789";

        StringBuilder builder = new StringBuilder();
        for (int i = 0;i<count;i++) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    } 
    
    public Boolean getAccess(int userId, String sessionId){
        Boolean rtn = false;
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
        }finally {
        	try {
        		if(con != null) con.close();
        		if(pr != null) pr.close();
        		if(rst != null) rst.close();
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	
        }
        return rtn;
    }

    /*for rotating imag file when file upload is have orientation data*/
    public BufferedImage rotateImage(int orientation, BufferedImage image){
        double rads = 0;
//        if(orientation == 6){
//            rads = Math.toRadians(-90);
//        }else if(orientation == 8){
//            rads = Math.toRadians(-90);
//        }else{
//            rads = Math.toRadians(180);
//        }

        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);

        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());

        AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);

        return rotatedImage;
    }

    public String readPass(String pass){
        String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "!","@","#","$","%","^","&","*","(",")","_","+","=","{","}",":",";","<",">","?","/","|","1","2","3","4","5","6","7","8","9","0"};

        String passOri = "";
        String[] passArr = pass.split("-");
        for(int i = 0;i<passArr.length;i++){
            try{
                BigDecimal passDec = new BigDecimal(passArr[i]);
                BigDecimal binary = passDec.divide(new BigDecimal(3429));
                String binaryString = String.valueOf(binary);
                int decimal = Integer.parseInt(binaryString,2);
                passOri += arr[decimal];
            }catch (Exception e){
                /*nothing*/
            }
        }
        return passOri;
    }

    public Boolean loginUserId(int userId, String password){
        DBConnection gc = new DBConnection();
        Connection conn = gc.getConnection();
        PreparedStatement pr = null;
        ResultSet rst = null;
        Boolean rtn = false;

        String sql = "SELECT COUNT(*) count_user FROM USER WHERE user_id = ? AND user_password = ?";
        try{
            pr = conn.prepareStatement(sql);
            pr.setInt(1, userId);
            pr.setString(2, password);
            rst = pr.executeQuery();
            if(rst.next()){
                if(rst.getInt("count_user") > 0) rtn = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return rtn;
    }
}