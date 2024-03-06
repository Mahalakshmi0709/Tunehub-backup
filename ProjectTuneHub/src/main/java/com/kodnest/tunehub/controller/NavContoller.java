package com.kodnest.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavContoller {
	
	
	@GetMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value="/registration")
	public String registration() {
		return "registration";
	}
	
	
	@GetMapping(value="/newsong")
	public String newsong() {
		return "newsong";
	}
	
	
	
	
}
