package com.equipo4.nocturnemusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String showHomePage() {
        return "product/products";
    }
}
