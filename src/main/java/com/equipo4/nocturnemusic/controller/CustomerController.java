package com.equipo4.nocturnemusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.equipo4.nocturnemusic.service.*;
import com.equipo4.nocturnemusic.model.*;

@Controller
public class CustomerController {
	@Autowired
	private ProductService prodService;
	@Autowired
	private CategoryService catService;
	
	@RequestMapping("/products/{category}")
	public String getProducts(@PathVariable("category") String category, Model model) {
		List<Category> categories = catService.findAll();
		model.addAttribute("categories", categories);
		
		if (category.equals("all")) {
			List<Product> products = prodService.findAll();
			model.addAttribute("products", products);
			model.addAttribute("title", "Our Products");
		} else {
			Category currentcat = catService.findByName(category)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
			List<Product> products = prodService.findByCategory(currentcat);
			model.addAttribute("products", products);
			String title = Character.toUpperCase(category.charAt(0)) + category.substring(1);
			model.addAttribute("title", title);
		}
		
		return "products";
	}
}