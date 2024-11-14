package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    // Dashboard page 
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // User profile page
    // Dashboard page 
    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }
    
    // user add contact page

    // user view contacts

    // user edit contacts

    // delete contact page
}
