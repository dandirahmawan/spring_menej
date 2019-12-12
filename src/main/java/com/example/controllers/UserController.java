package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SslEmail;
import com.example.Utils;
import com.example.model.BugsUserDetail;
import com.example.model.DataModule;
import com.example.model.DocumentFile;
import com.example.model.User;
import com.example.model.UserDetail;
import com.example.model.ViewUserRelation;
import com.example.service.BugsService;
import com.example.service.DocumentFileService;
import com.example.service.ModuleService;
import com.example.service.UserService;

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
	
	@GetMapping("/user")
	public List<User> getUser(){
		return us.getData();
	}

	@PostMapping("/user_relation")
	public List<ViewUserRelation> getUserRelation(@RequestParam int userId){
		return us.getUserRelation(userId);
	}

	@PostMapping("/user_detail")
	//user is paramter of user id who login in application
	public List<UserDetail> getUserDetail(@RequestParam int userId, int user, String sessionId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(user, sessionId);

		List<UserDetail>  udl = new ArrayList<UserDetail>();

		if(isAccess){
			List<BugsUserDetail> bud = bs.getDataBugsUser(userId);
			List<DocumentFile> docFile =dfs.getDocByUser(userId);
			List<DataModule> modul = ms.getDataByUserId(userId);
			UserDetail ud = new UserDetail(bud, modul, docFile);
			udl.add(ud);
		}

		return udl;
	}

	@PostMapping("/invitation")
	public String invitingUser(String email){
		String[] em = email.split(" ");
	    for(String item : em){
	    	Utils ut = new Utils();
	    	String codeConfirm = ut.RandomString(10);
	    	SslEmail se = new SslEmail();
		    se.sendEmail(item, codeConfirm);
		}
		return "success";
	}
}
