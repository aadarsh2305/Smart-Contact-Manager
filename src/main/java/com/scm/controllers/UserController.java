package com.scm.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.exception.Helper;



@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // Dashboard page: Support both GET and POST
    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String userDashboard() {
        return "user/dashboard";
    }

    // User profile page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile(Authentication authentication) {
        String userName = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("Logged in user got it from princeple class: "+ userName);

        // Database se data fetch karenge
        return "user/profile";
    }
    
    // user view contacts

    // user edit contacts

    // delete contact page
}
