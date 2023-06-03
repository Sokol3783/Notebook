package com.example.notebook.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")

public class HomeController {

  @GetMapping("/home")
  public String home() {
    return "home.html";
  }

  @Controller
  @RequestMapping("/")
  public static class IndexController{

    @GetMapping("/")
    public String index() {
      return "redirect:/home";
    }
  }
}
