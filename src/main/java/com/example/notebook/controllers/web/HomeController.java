package com.example.notebook.controllers.web;

import com.example.notebook.security.AccountDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

  AccountDetailsService service;

  public HomeController(AccountDetailsService service) {
    this.service = service;
  }

  @GetMapping("/home")
  public String home() {
    System.out.println("redirect to home");
    return "redirect:/home";
  }

  @RequestMapping(value = "/")
  public String index() {
    System.out.println("redirect to home");
    return "redirect:/home";
  }

  @GetMapping("/register")
  public String register() {
    System.out.println("register request");
    return "redirect:/register";
  }

  @GetMapping("/restore")
  public String forgetPassword(){
    return "redirect:/error/notfound.html";
  }

  @PostMapping(value = "/home", params = {"login", "pass"})
  public String login(Model model,
      @RequestParam("login") String username,
      @RequestParam("pass") String password) {
    //TODO
    UserDetails userDetails = service.loadUserByUsername(username);
    return "redirect:/home";
  }

}
