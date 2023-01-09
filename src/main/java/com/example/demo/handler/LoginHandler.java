package com.example.demo.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String redirectURL = request.getContextPath();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//        if(userDetails.getUsername().equals("trainee")){
//            redirectURL = "/logout";
//        }else if(userDetails.getUsername().equals("admin")){
//            redirectURL = "/api/v1/students";
//        }

        response.sendRedirect(redirectURL);
    }
}
