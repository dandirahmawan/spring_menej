package com.menej.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import com.menej.model.view.ViewUserRelationMember;
import com.menej.repo.ViewUserRelationMemberRepo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menej.DBConnection;
import com.menej.SslEmail;
import com.menej.Utils;
import com.menej.model.DataModule;
import com.menej.model.db.Invitation;
import com.menej.model.StartData;
import com.menej.model.db.User;
import com.menej.model.UserDetail;
import com.menej.model.db.UserRelation;
import com.menej.model.view.ViewBugs;
import com.menej.model.view.ViewDocumentFile;
import com.menej.repo.UserRelationRepo;
import com.menej.repo.UserRepo;
import com.menej.service.BugsService;
import com.menej.service.DocumentFileService;
import com.menej.service.InvitationService;
import com.menej.service.ModuleService;
import com.menej.service.PermitionService;
import com.menej.service.UserRelationService;
import com.menej.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	@Autowired
	UserService us;

	@Autowired
	BugsService bs;

	@Autowired
	DocumentFileService dfs;

	@Autowired
	ModuleService ms;
	
	@Autowired
	InvitationService is;
	
	@Autowired
	UserRelationRepo urr;
	
	@Autowired
	UserRelationService urs;
	
	@Autowired
	PermitionService ps;
	
	@Autowired
	UserRepo ur;

	@Autowired
    ViewUserRelationMemberRepo vurmr;
	
	@PostMapping("/user_data")
	public User getUserData(int userId, String sessionId) {
		User user = new User();
		Utils utils = new Utils();
		Boolean isAcces = utils.getAccess(userId, sessionId);
		if(isAcces) {
			user = us.getDataById(userId);
		}
		return user;
	}

	@PostMapping("/user_relation")
	public List<ViewUserRelationMember> getUserRelation(@RequestParam int userId){
		return vurmr.findByUserAccessingQuery(userId);
	}

	@PostMapping("/user_detail")
	//user is parameter of user id who login in application
	public List<UserDetail> getUserDetail(@RequestParam int userId, int user, String sessionId){
		List<UserDetail>  udl = new ArrayList<UserDetail>();
		List<ViewBugs> bud = bs.getViewBugsByUserIdDetail(userId, user);
		List<ViewDocumentFile> docFile = dfs.getDocByUserDetail(userId, user);
		List<DataModule> modul = ms.getDataByUserIdDetail(user);
		UserDetail ud = new UserDetail(bud, modul, docFile);
		udl.add(ud);


		return udl;
	}

	@PostMapping("/start_data")
	public StartData startData(int userId, String sessionId) {
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		StartData sd = new StartData();
		
		if(isAccess) {
			User user = us.getDataById(userId);
			String userName = user.getUserName();
			String userEmail = user.getEmailUser();
			String picProfile = user.getPicProfile();
			String picProfileDetail = user.getPicProfileDetail();
			sd.setName(userName);
			sd.setEmail(userEmail);
			sd.setUnreadNotif("0");
			sd.setPicProfile(picProfile);
			sd.setPicProfileDetail(picProfileDetail);
		}
		
		return sd;
	}
	
	@PostMapping("/delete_user")
	public String deleteUser(int userId, String sessionId, int userIdLogin) {
		Utils utils = new Utils();
		Boolean isAccess = false;
		
		isAccess = utils.getAccess(userIdLogin, sessionId);
		if(isAccess) {
			urs.deleteUserRelation(userId, userIdLogin);
		}
		
		return null;
	};
	
	@PostMapping("/register")
	public String regsiter(String email, String name, String password) {
		Utils utils = new Utils();
		String code = utils.RandomNumber(6);
		String reg = us.register(email, name, password, code);
		if(!reg.equals("ready")) {
			SslEmail sslEmail = new SslEmail();
			sslEmail.sendEmailRegistration(email, code);
		}
		return reg;
		
	}

    @PostMapping("/edit_user")
    public User rename(int userId, String userName, int ort, String base64, String base64Detail, String deletePicture) {
        String pattern = "yyyyMMddhhmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateString = simpleDateFormat.format(new Date());
        Boolean isDeletePic = Boolean.parseBoolean(deletePicture);

        String pic = "pp_"+dateString+userId+".jpg";
        String picDetail = "pp_detail_"+dateString+userId+".jpg";

        /*create directory*/
        File dir = new File("../upload/pic_profile");
		if(!dir.exists()) dir.mkdir();

        if(!base64Detail.equals("")){
            String[] sourceData = base64Detail.split(",");
            BufferedImage bufferedImage = null;
            byte[] imageByte;
            try {
                imageByte = Base64.getDecoder().decode(sourceData[1]);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageByte);
                bufferedImage = ImageIO.read(byteArrayInputStream);
                byteArrayInputStream.close();

                Utils utils = new Utils();
                BufferedImage rotatedImage = bufferedImage;
                if(ort != 0 && ort != 1) rotatedImage = utils.rotateImage(ort, bufferedImage);

                File file = new File("../upload/pic_profile/"+picDetail);
                ImageIO.write(rotatedImage, "jpg", file);

                File file2 = new File("../upload/pic_profile/"+pic);
                ImageIO.write(rotatedImage, "jpg", file2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        User user = ur.findByUserId(userId);
        if(!base64.equals("")) {
            File file = new File("../upload/pic_profile/" + user.getPicProfile());
            File file1 = new File("../upload/pic_profile/" + user.getPicProfileDetail());
            if (file.exists()) file.delete();
            if (file1.exists()) file1.delete();
        }

        //delete pic profile without change pic profile
        if(isDeletePic){
            File file = new File("../upload/pic_profile/" + user.getPicProfile());
            File file1 = new File("../upload/pic_profile/" + user.getPicProfileDetail());

            if (file.exists()) file.delete();
            if (file1.exists()) file1.delete();

            user.setPicProfile("");
            user.setPicProfileDetail("");
        }

        user.setUserName(userName);
        if(!base64.equals("")) user.setPicProfile(pic);
        if(!base64Detail.equals("")) user.setPicProfileDetail(picDetail);
        ur.save(user);
        return user;
    }
	
	@PostMapping("/confirmation_registration")
	public int confirm(String email, String confirmationCode) {
		return us.confirmRegistration(email, confirmationCode);
	}

	@PostMapping("/user_change_email")
	public JSONObject changeEmail(int userId, String email, String pass){
		Utils utils = new Utils();
		String passx = utils.readPass(pass);

		int cgEmail = us.changeEmailService(userId, email, passx);
		JSONObject responseJson = new JSONObject();

		if(cgEmail == 0){
			responseJson.put("code", 1);
			responseJson.put("message", "request change email successfully");
		}else if(cgEmail == 1){
			responseJson.put("code", 2);
			responseJson.put("message", "Password is wrong");
		}else if(cgEmail == 2){
			responseJson.put("code", 3);
			responseJson.put("message", "Email is not available");
		}

        return responseJson;
	}

	@PostMapping("/change_password")
	public int changePassword(String newPass, String password, int userId){
			int rtn = 0;
			Utils utils = new Utils();

			Boolean valid = utils.loginUserId(userId, password);
			System.out.println(valid);
			if(valid){
				/*update data user*/
				DBConnection gc = new DBConnection();
				Connection conn = gc.getConnection();
				PreparedStatement pr = null;
				String sql = "UPDATE user SET user_password = ? WHERE user_id = ?";

				try{
					pr = conn.prepareStatement(sql);
					pr.setString(1, newPass);
					pr.setInt(2, userId);
					int exc = pr.executeUpdate();
					System.out.println(exc);
				}catch (Exception e){
					e.printStackTrace();
				}finally {
					try{
						if(conn != null) conn.close();
						if(pr != null) pr.cancel();
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}else{
				rtn = 2;
			}

		/*
		0 => for change password sucessfully
		2 => for password not match
		 */
		return rtn;
	}
}
