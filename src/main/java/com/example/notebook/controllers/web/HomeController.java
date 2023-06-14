package com.example.notebook.controllers.web;

import com.example.notebook.entity.Account;
import com.example.notebook.security.AccountDetailsService;
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

  @PostMapping(value = "/login")
  public String login(Model model,
      @RequestParam("login") String username,
      @RequestParam("pass") char[] password) {
    Account user = service.authorizeUser(username, password.toString());
    if (user.isEnabled()) {
      return "redirect:/notebook/";
    }
    model.addAttribute("pass", null);
    return model.toString();
  }
}
