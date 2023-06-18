package com.example.notebook.web.controllers;

import com.example.notebook.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

  @GetMapping(value = "/")
  public String index() {
    return "redirect:/a/home";
  }

  @GetMapping(value = "/a/home")
  public String home(Model model, @ModelAttribute("loginDTO") LoginDTO login) {
    model.addAttribute("loginDTO", new LoginDTO());
    return "/anonymous-pages/home";
  }

}
