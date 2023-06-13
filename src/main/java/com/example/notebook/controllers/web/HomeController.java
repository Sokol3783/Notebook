package com.example.notebook.controllers.web;

import com.example.notebook.security.AccountDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  AccountDetailsService service;

  public HomeController(AccountDetailsService service) {
    this.service = service;
  }

  @GetMapping(value = "/")
  public String index() {
    return "redirect:/home";
  }

  @GetMapping(value = "/home")
  public String home() {
    return "home";
  }

  @PostMapping(value = "/home", params = {"login", "pass"})
  public String login(Model model,
      @RequestParam("login") String username,
      @RequestParam("pass") CharSequence password) {
    UserDetails user = service.authorizeUser(username, password);
    if (user.isEnabled()) {
      return "redirect:/pages/notebook/";
    }
    model.addAttribute("pass");
    return model.toString();
  }
}
