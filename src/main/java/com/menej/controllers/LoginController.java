package com.menej.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.menej.repo.UserRepo;
import com.menej.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menej.DBConnection;
import com.menej.Utils;
import com.menej.model.UserLogin;

@RestController
public class LoginController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	LoginService loginService;

	@PostMapping("/login")
	public List<Object> login(@RequestParam String email, String password) {
		Utils utils = new Utils();
		String pass = utils.readPass(password);
		return loginService.login(email, pass);
	}

	@PostMapping("/loginApp")
	public List<Object> loginApp(@RequestParam String email, String password) {
		return loginService.login(email, password);
	}
}
