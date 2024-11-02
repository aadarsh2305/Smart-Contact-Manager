package com.scm.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("name", "Substring Tech");
		model.addAttribute("YT", "https://chatgpt.com/");
		model.addAttribute("Github", "https://github.com/aadarsh2305");
		return "home";
	}

	@RequestMapping("/about")
	public String about(){
		System.out.println("About page loaded");
		return "about";
	}
	
	@RequestMapping("/services")
	public String services(){
		System.out.println("Services page loaded");
		return "services";
	}

	@RequestMapping("/contact")
	public String contact(){
		System.out.println("Contact page loaded");
		return "contact";
	}

	@RequestMapping("/login")
	public String login(){
		System.out.println("login page loaded");
		return "login";
	}

	@RequestMapping("/register")
	public String register(){
		System.out.println("register page loaded");
		return "register";
	}
}
