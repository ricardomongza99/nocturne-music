package com.equipo4.nocturnemusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import com.equipo4.nocturnemusic.model.*;

// En lo que resuelvo HOME
@SuppressWarnings("unused")

@Controller
public class AppController {
    @RequestMapping("/")
    public String landing() {
        return "index";
    }

    @RequestMapping("/home")
	public String home(Model model, HttpSession session) {
		
		return "home";
	}
}

