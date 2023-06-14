package com.example.notebook.controllers.web;

import com.example.notebook.dto.LoginDTO;
import com.example.notebook.entity.Account;
import com.example.notebook.security.AccountDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
  public String home(Model model) {
    if (model.containsAttribute("loginDTO")) {
      LoginDTO loginDTO = (LoginDTO) model.getAttribute("loginDTO");
      loginDTO.setPassword(null);
      model.addAttribute(loginDTO);
    }
    return "home";
  }

  @PostMapping(value = "/home")
  public String login(HttpSession session, @ModelAttribute("loginDTO") LoginDTO loginDTO) {
    Account account = service.authorizeUser(loginDTO.getLogin(), loginDTO.getPassword().toString());
    if (account.isEnabled()) {
      session.setAttribute("account", account);
      return "redirect:/pages/notebook/";
    }
    return "/home";
  }

}
