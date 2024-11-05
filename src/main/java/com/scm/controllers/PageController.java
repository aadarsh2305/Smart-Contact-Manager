package com.scm.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;

@Controller
public class PageController {

	@Autowired
	private UserService userService;
	
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

	@GetMapping("/login")
	public String login(){
		System.out.println("login page loaded");
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model){
		System.out.println("register page loaded");

		UserForm userForm =  new UserForm();
		// userForm.setName("Addy");
		// userForm.setEmail("Addy@gmail.com");
		// userForm.setPassword("98765678");
		// userForm.setPhoneNumber("1234567890");
		// userForm.setAbout("Hi, There i am Aadarsh from Indore");
		model.addAttribute("userForm", userForm);
		return "register";
	}

	// Processing Register page	
	@PostMapping("/do-register")
	public String processRegister(@ModelAttribute UserForm userForm){
		System.out.println("processRegister method called");
		System.out.println(userForm);

		// UserForm -> User -> save to database
		User user = User.builder()
		.name(userForm.getName())
		.email(userForm.getEmail())
		.password(userForm.getPassword())
		.phoneNumber(userForm.getPhoneNumber())
		.about(userForm.getAbout())
		.profilePic("https://aadarsh-folio.vercel.app/assets/pngtree-user-profile-avatar-png-image_10211467-DxoXaHBV.png")
		.build();
		userService.save(user);
		// Fetch the data
		System.out.println("usersaved from processRegister method:: USER :: "+user);
		
		return "redirect:/register";
	}
}
