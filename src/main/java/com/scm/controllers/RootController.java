package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.exception.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {

        private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if(authentication == null){
            return;
        }
        String userName = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("Logged in user got it from princeple class: " + userName);
        // Database se data fetch karenge
        User user = userService.getUserByEmail(userName);
        if (user == null) {
            model.addAttribute("loggedInUser", null);
        } else {
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            // Ye yaha se jayega view par jise hum render kar rhe h profile page par
            model.addAttribute("loggedInUser", user);
        }
    }
}
