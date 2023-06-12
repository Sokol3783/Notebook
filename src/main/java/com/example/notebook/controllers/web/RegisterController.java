package com.example.notebook.controllers.web;

import com.example.notebook.entity.Account;
import com.example.notebook.repository.AccountRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

  private final AccountRepository accountRepository;

  public RegisterController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("accountDTO", new AccountDTO());
    return "/register";
  }

  //TODO bad smell
  @PostMapping("/register")
  public String creatAccount(@Valid @ModelAttribute("accountDTO") AccountDTO account, Model model,
      BindingResult result) {

    if (account.getAccount().getPassword() == null | account.repeatedPassword == null) {
      model.addAttribute("mismatch", true);
      return "/register";
    }

    if (result.hasErrors()) {
      return clearModel(model);
    }

    if (account.getAccount().getPassword().compareTo(account.getRepeatedPassword()) == 0) {
      accountRepository.save(account.account);
      model.addAttribute("successful register", true);
      return "redirect:/home";
    }

    return clearModel(model);
  }

  private String clearModel(Model model) {
    model.addAttribute("password");
    model.addAttribute("passwordRepeat");
    return "/register";
  }


  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  public static class AccountDTO {

    Account account;
    String repeatedPassword;
  }


}
