package com.example.notebook.web.controllers;

import com.example.notebook.dto.AccountDTO;
import com.example.notebook.entity.Account;
import com.example.notebook.repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotebookController {

  @Autowired
  private MySessionInfo mySessionInfo;

  final int pageSize = 20;
  private final AccountRepository accountRepository;

  public NotebookController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }


  @GetMapping("/u/notebook")
  public String openNotebook(@ModelAttribute("account") AccountDTO account, Model model) {
    return "redirect:/u/notebook/1?sort-field=id&sort-dir=asc";
  }

  @GetMapping("/u/notebook/{pageNo}")
  public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
      @RequestParam("sortField") String sortField,
      @RequestParam("sortDir") String sortDir,
      Model model) {

    /*
    Page <Account> page = accountRepository.findPaginated(pageNo, pageSize, sortField, sortDir);
    List< Accoun > listEmployees = page.getContent();

    model.addAttribute("currentPage", pageNo);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("totalItems", page.getTotalElements());

    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

    model.addAttribute("listEmployees", listEmployees);
    return "index";
     */
    //return "/u/notebook/{}";
    return null;
  }


}
