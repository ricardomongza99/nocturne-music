package com.equipo4.nocturnemusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping("/")
    public String showHomePage() {
        return "product/products";
    }
}
