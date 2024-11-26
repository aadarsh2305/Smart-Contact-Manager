package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.SecurityCustomUserDetailService;

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

    @Autowired
    private OAuthAuthenicationSuccessHandler handler;

    // Database se user nikal rahe h for checking and login
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userDetailService);
        dap.setPasswordEncoder(passwordEncoder());

        return dap;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Configuration for routes security
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();

            // Private routes
            authorize.requestMatchers("/user/**").authenticated();

            // Public routes
            authorize.anyRequest().permitAll();
        });

        // form default login
        // If kuch bhi change krna hua to hum yaha aayenge : form login se related
        // httpSecurity.formLogin(Customizer.withDefaults());
        // return httpSecurity.build();
        // Customize springsecurity ka default login page 
        httpSecurity.formLogin(formLogin -> {
            formLogin
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    // .defaultSuccessUrl("/user/dashboard",true)
                    .successForwardUrl("/user/dashboard")
                    // .failureUrl("/login?error=true")
                    // .failureForwardUrl("/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {
            //         @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            //         throw new UnsupportedOperationException("Not supported yet.");
            //     }
            // });
            // formLogin.successHandler(new AuthenticationSuccessHandler() {
            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
            // });
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        
        httpSecurity.logout(logout -> {
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/login?logout=true");
        });

        // oauth configuration
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
