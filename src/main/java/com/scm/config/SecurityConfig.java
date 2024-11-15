package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.SecurityCustomUserDetailService;

import jakarta.annotation.security.PermitAll;

@Configuration
public class SecurityConfig {

    // User create and login using Java within memory service
    
    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails user1 = User.withUsername("Admin123")
    //                             .roles("Admin")
    //                             .password("admin123")
    //                             .build();

    //     UserDetails user2 = User.withUsername("Abc")
    //                             .roles("Guest")
    //                             .password("abc")
    //                             .build();

    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
    //     return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userDetailService);
        dap.setPasswordEncoder(passwordEncoder());

        return dap;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // Configuration for routes security
        httpSecurity.authorizeHttpRequests(authorize ->{
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            
            // Private routes
            authorize.requestMatchers("/user/**").authenticated();
            
            // Public routes
            authorize.anyRequest().permitAll();
        });

        // form default login
        // If kuch bhi change krna hua to hum yaha aayenge : form login se related
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
