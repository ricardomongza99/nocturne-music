package com.equipo4.nocturnemusic.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.equipo4.nocturnemusic.service.*;
import com.equipo4.nocturnemusic.model.*;

@Controller
public class CustomerController {
	private final ProductService prodService;
	private final CategoryService catService;
	private CartService cartService;
	
	@Autowired
	public CustomerController(ProductService ps, CartService cs, CategoryService ks) {
		this.prodService = ps;
		this.cartService = cs;
		this.catService = ks;
	}
	
	@RequestMapping("/products/{category}")
	public String getProducts(@PathVariable("category") String category, Model model) {
		model.addAttribute("categories", catService.findAll());
		
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
	
	@RequestMapping("/cart")
	public String checkout(Model model, HttpSession session) {
		var cartService = (CartService)session.getAttribute("cart");
		if (cartService == null) {
			User user = (User)session.getAttribute("active_user");
			this.cartService.newCart(user);
		} else this.cartService = cartService;
		model.addAttribute("cart", this.cartService.ticket());
		model.addAttribute("categories", catService.findAll());
		model.addAttribute("grandTotal", this.cartService.grandTotal());
		return "cart";
	}
	
	@PostMapping("/addToCart")
	public String addToCart(Model model, HttpSession session, HttpServletRequest request,
							@RequestParam("id") Long iid) {
		var cartService = (CartService)session.getAttribute("cart");
		if (cartService == null) {
			User user = (User)session.getAttribute("active_user");
			this.cartService.newCart(user);
		} else this.cartService = cartService;
		Product item = prodService.findById(iid)
		        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + iid));
		this.cartService.addToCart(item);
		session.setAttribute("cart", this.cartService);
		
		String location = request.getHeader("Referer");
	    return "redirect:" + location;
	}
	
	@PostMapping("/removeFromCart")
	public String removeFromCart(Model model, HttpSession session,
								 @RequestParam("id") Long iid) {
		var cartService = (CartService)session.getAttribute("cart");
		if (cartService == null) {
			User user = (User)session.getAttribute("active_user");
			this.cartService.newCart(user);
		} else this.cartService = cartService;
		Product item = prodService.findById(iid)
		        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + iid));
		this.cartService.removeFromCart(item);
		session.setAttribute("cart", this.cartService);
		return "redirect:/cart";
	}
	
	@RequestMapping("/thanks")
	public String end(Model model, HttpSession session) {
		User user = (User)session.getAttribute("active_user");
		var cartService = (CartService)session.getAttribute("cart");
		if (cartService == null) {
			return "home";
		} else this.cartService = cartService;
		model.addAttribute("user", user.getName());
		model.addAttribute("categories", catService.findAll());
		this.cartService.newCart(user);
		return "thanks";
	}
	
	@RequestMapping("/logout")
	public String cleanCart(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}