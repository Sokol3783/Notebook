package com.example.notebook.web.controllers;

import com.example.notebook.repository.AccountRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotebookController {

  private final AccountRepository accountRepository;

  public NotebookController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @GetMapping("/tutorials")
  public String getAll(Model model, @RequestParam(required = false) String keyword,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "15") int size,
      @RequestParam(defaultValue = "id,asc") String[] sort) {
    /*
    try {
      List<Notice> tutorials = new ArrayList<Notice>();


      String sortField = sort[0];
      String sortDirection = sort[1];

      Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
      Order order = new Order(direction, sortField);

      Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

      Page<Tutorial> pageTuts;
      if (keyword == null) {
        pageTuts = tutorialRepository.findAll(pageable);
      } else {
        pageTuts = tutorialRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        model.addAttribute("keyword", keyword);
      }

      tutorials = pageTuts.getContent();

      model.addAttribute("tutorials", tutorials);
      model.addAttribute("currentPage", pageTuts.getNumber() + 1);
      model.addAttribute("totalItems", pageTuts.getTotalElements());
      model.addAttribute("totalPages", pageTuts.getTotalPages());
      model.addAttribute("pageSize", size);
      model.addAttribute("sortField", sortField);
      model.addAttribute("sortDirection", sortDirection);
      model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }
       */

    return "/user-pages/notebook";
  }

  private static List<Integer> getPageSizes(){
    return List.of(10, 15, 20);
  }


}
