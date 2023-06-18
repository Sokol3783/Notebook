package com.example.notebook.web.controllers;

import com.example.notebook.dto.NoteDTO;
import com.example.notebook.dto.NotebookDTO;
import com.example.notebook.entity.Note;
import com.example.notebook.repository.NoteRepository;
import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotebookController {

  private final NoteRepository repository;
  private final HttpSession httpSession;

  public NotebookController(NoteRepository repository, HttpSession httpSession) {
    this.repository = repository;
    this.httpSession = httpSession;
  }

  @GetMapping("/u/notebook")
  public String getNotes(Model model, @ModelAttribute("NotebookDTO") NotebookDTO notebook) {
    try {
      Order order = buildNotebookOrder(model, notebook);

      Pageable pageable = PageRequest.of(notebook.getPageNumber() - 1, notebook.getPageSize(),
          Sort.by(order));

      Page<Note> pageNotes = getPagesForKeyword(model, notebook, pageable);
      List<Note> notes = pageNotes.getContent();

      model.addAttribute("notes", notes);
      model.addAttribute("currentPage", pageNotes.getNumber() + 1);
      model.addAttribute("totalItems", pageNotes.getTotalElements());
      model.addAttribute("totalPages", pageNotes.getTotalPages());
      model.addAttribute("pageSize", notebook.getPageSize());
      model.addAttribute("sizesOfPage", notebook.getSizesOfPage());

    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }
    return "/user-pages/notebook";
  }

  private Page<Note> getPagesForKeyword(Model model, NotebookDTO notebook, Pageable pageable) {
    Long id = (Long) httpSession.getAttribute("id");
    if (notebook.getKeyword() == null) {
      return repository.findAllByOwnerId(id, pageable);
    } else {
      model.addAttribute("keyword", notebook.getKeyword());
      return repository.findByKeyword(
          notebook.getKeyword(), pageable, id);
    }
  }

  private Order buildNotebookOrder(Model model, NotebookDTO notebook) {
    String sortField = notebook.getSort().get(0);
    String sortDirection = notebook.getSort().get(1);

    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDirection", sortDirection);
    model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

    Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    return new Order(direction, sortField);
  }

  @PostMapping("/u/notebook")
  public String addNote(Model model, @ModelAttribute("NoteDTO") NoteDTO noteTDO) {
    return "redirect:/user-pages/notebook";
  }

}
