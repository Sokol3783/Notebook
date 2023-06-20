package com.example.notebook.security;

import com.example.notebook.entity.Account;
import com.example.notebook.repository.AccountRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

  HttpSession session;

  AccountRepository repo;
  private static final Logger logger = LoggerFactory.getLogger(
      AuthenticationSuccessHandlerImpl.class);

  public AuthenticationSuccessHandlerImpl(HttpSession session, AccountRepository repo) {
    this.session = session;
    this.repo = repo;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    String userName = "";
    if (authentication.getPrincipal() instanceof Principal) {
      userName = ((Principal) authentication.getPrincipal()).getName();

    } else {
      userName = ((User)authentication.getPrincipal()).getUsername();
    }

    logger.info("principal userName: " + userName);
    Account principal = repo.findByLogin(userName).get();
    logger.info(principal.getLogin() + " is successful login!");
    session.setAttribute("id", principal.getId());
    logger.info("Id is - " + session.getAttribute("id"));

    response.sendRedirect("/u/notebook");
  }
}
