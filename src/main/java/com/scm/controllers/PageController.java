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
	public String about(Model model){
		System.out.println("About page loaded");
		return "about";
	}
	
	@RequestMapping("/services")
	public String services(Model model){
		System.out.println("Services page loaded");
		return "service";
	}
}
