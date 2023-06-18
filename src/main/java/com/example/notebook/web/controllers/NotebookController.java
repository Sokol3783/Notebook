package com.example.notebook.web.controllers;

import com.example.notebook.dto.NotebookDTO;
import com.example.notebook.entity.Note;
import com.example.notebook.repository.NoteRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class NotebookController {

  private final NoteRepository repository;

  public NotebookController(NoteRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/u/notebook")
  public String getNotes(Model model, @ModelAttribute("NotebookDTO") NotebookDTO notebook) {
    try {
      List<Note> notes = new ArrayList<>();
      String sortField = notebook.getSort().get(0);
      String sortDirection = notebook.getSort().get(1);

      Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
      Order order = new Order(direction, sortField);

      Pageable pageable = PageRequest.of(notebook.getPageNumber() - 1, notebook.getPageSize(),
          Sort.by(order));

      Page<Note> pageNotes;
      if (notebook.getKeyword() == null) {
        pageNotes = repository.findAll(pageable);
      } else {
        pageNotes = repository.findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrSecondNameIgnoreCase(
            notebook.getKeyword(), pageable);
        model.addAttribute("keyword", notebook.getKeyword());
      }

      notes = pageNotes.getContent();

      model.addAttribute("notes", notes);
      model.addAttribute("currentPage", pageNotes.getNumber() + 1);
      model.addAttribute("totalItems", pageNotes.getTotalElements());
      model.addAttribute("totalPages", pageNotes.getTotalPages());
      model.addAttribute("pageSize", notebook.getPageSize());
      model.addAttribute("sortField", sortField);
      model.addAttribute("sortDirection", sortDirection);
      model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "/user-pages/notebook";
  }

}
