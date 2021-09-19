package com.menej.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.menej.service.UserService;

@RestController
public class HomeController {
	@Autowired
	UserService us;
	
	@GetMapping({"/", "/index"})
	public String home() {
		return "API IS RUNNING"; 
	}	
}
