package com.example.notebook.web.controllers;

import com.example.notebook.dto.RegisterDTO;
import com.example.notebook.entity.Account;
import com.example.notebook.mapper.RegisterMapper;
import com.example.notebook.repository.AccountRepository;
import jakarta.validation.Valid;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

  private final AccountRepository accountRepository;
  private final RegisterMapper mapper;

  public RegisterController(AccountRepository accountRepository, RegisterMapper mapper) {
    this.accountRepository = accountRepository;
    this.mapper = mapper;
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("register", new RegisterDTO());
    return "/anonymous-pages/register";
  }

  @PostMapping("/a/register")
  public String creatAccount(@Valid @ModelAttribute("register") RegisterDTO register, Model model,
      BindingResult result) {

    if (result.hasErrors()) {
      System.out.println("This was error " + result);
      return clearPasswords(model);
    }

    if (Arrays.equals(register.getPassword(), register.getRepeatedPassword())) {
      return saveAccount(model, register);
    }
    model.addAttribute("mismatch", true);
    return "/anonymous-pages/register";
  }

  private String saveAccount(Model model, RegisterDTO register) {
    Account mapping = mapper.mapping(register);
    accountRepository.save(mapping);
    model.addAttribute("successful register", true);
    return "redirect:/home";
  }

  private String clearPasswords(Model model) {
    model.addAttribute("password");
    model.addAttribute("repeatedPassword");
    return "/anonymous-pages/register";
  }
}
