package com.example.travelproject.config.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.travelproject.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Autowired
  @Lazy
  private UserService userService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
      log.info("[LoginAuthSuccessHandler][onAuthenticationSuccess] Start");
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    userService.updateIsLoginById(userDetails.getUsername(), true);
    if (userDetails.getUsername() == null) {
      response.sendRedirect("/index");
    } else if (userDetails.getUsername().equals("admin")) {
      response.sendRedirect("/admin/index");
    } else {
      response.sendRedirect("/user/index");
    }
    super.onAuthenticationSuccess(request, response, authentication);
  }


}
