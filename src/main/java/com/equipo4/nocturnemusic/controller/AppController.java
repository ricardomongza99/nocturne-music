package com.equipo4.nocturnemusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import com.equipo4.nocturnemusic.model.*;
import com.equipo4.nocturnemusic.service.*;

@Controller
public class AppController {
	private final UserService userService;
	
	@Autowired
	public AppController(UserService userService) {
		this.userService = userService;
	}
    
    @RequestMapping("/")
    public String landing() {
        return "index";
    }

    @RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}
    
    @GetMapping("/login")
    public String showLogin() {
        return "account/sign-in";
    }

    @PostMapping("/login")
    public String processLogin(Model model, HttpSession session,
    		@RequestParam("username") String username,
    		@RequestParam("password") String password
    	) {
    	User user = userService.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
        	session.setAttribute("active_user", user.getId());
            if (user.isAdmin()) {
                return "redirect:/fork";
            } else {
                return "redirect:/home";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "account/sign-in";
        }
    }
    
    @RequestMapping("/fork")
	public String adminfork(Model model) {
		return "fork";
	}
}