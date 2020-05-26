package com.example.controllers;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DBConnection;
import com.example.SslEmail;
import com.example.Utils;
import com.example.model.BugsUserDetail;
import com.example.model.DataModule;
import com.example.model.DocumentFile;
import com.example.model.Invitation;
import com.example.model.StartData;
import com.example.model.User;
import com.example.model.UserDetail;
import com.example.model.UserRelation;
import com.example.model.view.ViewBugs;
import com.example.model.view.ViewDocumentFile;
import com.example.model.view.ViewUserRelation;
import com.example.repo.UserRelationRepo;
import com.example.repo.UserRepo;
import com.example.service.BugsService;
import com.example.service.DocumentFileService;
import com.example.service.InvitationService;
import com.example.service.ModuleService;
import com.example.service.PermitionService;
import com.example.service.UserRelationService;
import com.example.service.UserService;

import javax.imageio.ImageIO;

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
	public List<ViewUserRelation> getUserRelation(@RequestParam int userId){
		return us.getUserRelation(userId);
	}

	@PostMapping("/user_detail")
	//user is parameter of user id who login in application
	public List<UserDetail> getUserDetail(@RequestParam int userId, int user, String sessionId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(user, sessionId);

		List<UserDetail>  udl = new ArrayList<UserDetail>();

		if(isAccess){
			List<ViewBugs> bud = bs.getViewBugsByUserIdDetail(userId, user);
			List<ViewDocumentFile> docFile = dfs.getDocByUserDetail(userId, user);
			List<DataModule> modul = ms.getDataByUserIdDetail(userId, user);
			UserDetail ud = new UserDetail(bud, modul, docFile);
			udl.add(ud);
		}

		return udl;
	}

	@PostMapping("/invitation")
	public String invitingUser(String email, String userId, String sessionId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(Integer.parseInt(userId), sessionId);
		
		if(isAccess) {
			String[] em = email.split(" ");
		    for(String item : em){
		    	Utils ut = new Utils();;
		    	String codeConfirm = ut.RandomString(15);
		    	SslEmail se = new SslEmail();
			    se.sendEmail(item, codeConfirm);
			    
			    List<Invitation> invitations = new ArrayList<Invitation>();
			    invitations = is.getInvitationReady(email, "OK", Integer.parseInt(userId));
			    if(invitations.size() < 1) {
			    	is.insertInvitation(email, userId, codeConfirm);
			    }else {
			    	
			    }
			}
		}
		
		return "success";
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
			sd.setName(userName);
			sd.setEmail(userEmail);
			sd.setUnreadNotif("0");
			sd.setPicProfile(picProfile);
		}
		
		return sd;
	}
	
	@PostMapping("/conf_invitation")
	public int confInvitation(String sessionId, int userId, String conf) {
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		int rtn = 0;
		if(isAccess) {
			User user = us.getDataById(userId);
			String emailUser = user.getEmailUser();
			
			DBConnection gc = new DBConnection();
			Connection con = gc.getConnection();
			PreparedStatement pr = null;
			ResultSet rst = null;
			
			String sql = "SELECT user_id FROM invitation WHERE invitation_email = ? AND invitation_code = ?";
			
			try {
				pr = con.prepareStatement(sql);
				pr.setString(1, emailUser);
				pr.setString(2,  conf);
				rst = pr.executeQuery();
				if(rst.next()) {
					UserRelation userRelation = new UserRelation();
					userRelation.setUserOne(String.valueOf(userId));
					userRelation.setUserTwo(rst.getString("user_id"));
					userRelation.setRelateDate(new Date());
					urr.save(userRelation);
					rtn = 1;
					ps.deleteInvitation(emailUser, conf);
				}
				rst.close();
				con.close();
				pr.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
		}
		
		return rtn;
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
	
	@PostMapping("/rename_profile")
	public User rename(String sessionId, int userId, String name, int ort, String base64, String base64Detail) {

		String pattern = "yyyyMMddhhmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateString = simpleDateFormat.format(new Date());

		String pic = "pp_"+dateString+userId+".jpg";
		String picDetail = "pp_detail_"+dateString+userId+".jpg";

		System.out.println("base64 = "+base64);
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

		Utils utils = new Utils();
		String rtn = "0";
		Boolean isAccess = utils.getAccess(userId, sessionId);
		User user = ur.findByUserId(userId);
		if(isAccess) {
			if(!base64.equals("")) {
				File file = new File("../upload/pic_profile/" + user.getPicProfile());
				File file1 = new File("../upload/pic_profile/" + user.getPicProfileDetail());
				if (file.exists()) file.delete();
				if (file1.exists()) file1.delete();
			}

			user.setUserName(name);
			if(!base64.equals("")) user.setPicProfile(pic);
			if(!base64Detail.equals("")) user.setPicProfileDetail(picDetail);
			ur.save(user);
			rtn = (!base64.equals("") || !base64Detail.equals("")) ? "0" : "0,"+pic+"|"+picDetail;
		}
		return user;
	}
	
	@PostMapping("/confirmation_registration")
	public int confirm(String email, String confirmationCode) {
		return us.confirmRegistration(email, confirmationCode);
	}

	@PostMapping("/change_email")
	public int changeEmail(int userId, String sessionId, String email, String password){
		int rtn = 0;
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		if(isAccess){
			Boolean valid = utils.loginUserId(userId, password);
			if(valid){
				User user = us.getDataById(userId);
				user.setEmailUser(email);
				ur.save(user);
			}else{
				rtn = 2;
			}
		}else{
			rtn = 1;
		}
		return rtn;

		/*
		2 => password user is wrong
		1 => user session expired
		0 =? change email success
		 */
	}

	@PostMapping("/change_password")
	public int changePassword(String newPass, String password, int userId, String sessionId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		int rtn = 0;
		if(isAccess){
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
		}

		/*
		0 => for change password sucessfully
		2 => for password not match
		 */
		return rtn;
	}
}
