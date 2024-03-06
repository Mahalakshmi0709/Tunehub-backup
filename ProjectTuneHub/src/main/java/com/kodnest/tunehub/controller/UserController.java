package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.serviceimpl.UserServiceimpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserServiceimpl userServiceimpl;
	
	
	@Autowired
	SongService songService;

	@PostMapping(value="/register")
	public String addUser(@ModelAttribute User user) {

		//email taken from the registration form
		String email = user.getEmail();
		
		//checking entered email is already present in DB or not
		boolean status = userServiceimpl.emailExsits(email);
        if(status== false) {
			userServiceimpl.addUser(user);
			System.out.println("User added");

		}
		else {
			System.out.println("User already exists");

		}

		return "login";

	}

	@PostMapping(value="/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session, Model model) {
		
           if(userServiceimpl.validateUser(email, password) == true){
        	   
              String role = userServiceimpl.getRole(email);
              
              session.setAttribute("email", email);
              
                if(role.equals("admin")) {
                   return "adminhome";
                }
                else {
              User user = userServiceimpl.getUser(email);
              boolean userstatus = user.isIspremium();
              
              List<Song> fetchAllSongs = songService.fetchAllSongs();
              model.addAttribute("songs", fetchAllSongs); 
              
              
              model.addAttribute("ispremium", userstatus );
				return "customerhome";

			}

		}	

		else {

			return "login";

		}	

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}


	@GetMapping("/exploresongs")
	public String exploresongs(String email) {
		return email;


	}

}
