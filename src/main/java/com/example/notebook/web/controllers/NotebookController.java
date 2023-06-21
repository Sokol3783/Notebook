package com.example.notebook.web.controllers;

import com.example.notebook.dto.NoteDTO;
import com.example.notebook.dto.NotebookDTO;
import com.example.notebook.entity.Note;
import com.example.notebook.mapper.NoteMapper;
import com.example.notebook.repository.NoteRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
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
  private final NoteMapper mapper;

  public NotebookController(NoteRepository repository, HttpSession httpSession, NoteMapper mapper) {
    this.repository = repository;
    this.httpSession = httpSession;
    this.mapper = mapper;
  }

  @GetMapping("/u/notebook")
  public String getNotes(Model model, @ModelAttribute("notebook") NotebookDTO notebook,
      @ModelAttribute("noteDTO") NoteDTO note) {
    return fillModel(model, notebook);
  }

  private String fillModel(Model model, NotebookDTO notebook) {
    if (notebook.getPage().getCurrentPage() != notebook.getPage().getNewPage()) {
      try {
        fillPageFields(model, notebook);

        List<Note> notes = List.copyOf(
            refreshPageContent(notebook, buildNotebookOrder(model, notebook), model));
        model.addAttribute("notes", notes);


      } catch (Exception e) {
        model.addAttribute("message", e.getMessage());
      }
    }

    return "/user-pages/notebook";
  }

  @PostMapping("/u/notebook")
  public void form(Model model, @ModelAttribute("notebook") NotebookDTO notebook, @ModelAttribute("noteDTO") NoteDTO note) {
    getNotes(model, notebook, note);
  }

  private void fillPageFields(Model model, NotebookDTO notebook) {
    notebook.getPage().setCurrentPage(notebook.getPage().getNewPage());
    model.addAttribute("page", notebook.getPage());
  }

  private List<Note> refreshPageContent(NotebookDTO notebook, Order order,
      Model model) {
    Pageable pageable = PageRequest.of(notebook.getPage().getCurrentPage()-1, notebook.getPage().getPageSize(),
        Sort.by(order));

    Page<Note> pageNotes = getPagesForKeyword(model, notebook, pageable);
    notebook.getPage().setTotal(pageNotes.getTotalPages() / pageable.getPageSize()
      + pageNotes.getTotalPages() % pageable.getPageSize());
    model.addAttribute("totalPages", notebook.getPage().getTotal());
    return pageNotes.getContent();
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
    String sortField = notebook.getPage().getSortField();
    String sortDirection = notebook.getPage().getSortDirection();

    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDirection", sortDirection);
    model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

    Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    return new Order(direction, sortField);
  }


  @PostMapping("/u/notebook/note/create")
  public String addNote(Model model, @ModelAttribute("noteDTO") NoteDTO noteDTO,
      @ModelAttribute("notebook") NotebookDTO notebook) {
    noteDTO.setOwnerId((Long) httpSession.getAttribute("id"));
    Note note = repository.save(mapper.mapToObject(noteDTO));
    model.addAttribute("notebook", notebook);
    if (note == null && note.getId() != 0) {
      notebook.setMessage("Note was not saved");
      return "/user-pages/notebook";
    }
    return "redirect:/u/notebook";
  }


  @PostMapping("/u/notebook/note/update")
  public String update(Model model, @ModelAttribute("noteDTO") NoteDTO noteDTO,
      @ModelAttribute("notebook") NotebookDTO notebook) {
    Optional<Note> byId = repository.findById(Long.parseLong(noteDTO.getNoteId()));
    if (byId.isPresent()) {
      try {
        updateNames(noteDTO, byId.get());
        return "redirect:/u/notebook";
      } catch (Exception e) {
        notebook.setMessage(e.getMessage());
        return "/user-pages/notebook";
      }
    }
    notebook.setMessage("Update failed");
    return "/user-pages/notebook";
  }

  @PostMapping("/u/notebook/note/delete")
  public String delete(Model model, @ModelAttribute("noteDTO") NoteDTO noteDTO,
      @ModelAttribute("notebook") NotebookDTO notebook) {
    try {
      repository.deleteById(Long.parseLong(noteDTO.getNoteId()));
      return "redirect:/u/notebook";
    } catch (Exception e) {
      notebook.setMessage("Update failed");
    }
    return "/user-pages/notebook";
  }

  private void updateNames(NoteDTO noteDTO, Note note) {
    note.setFirstName(noteDTO.getNoteFirstName());
    note.setLastName(noteDTO.getNoteLastName());
    note.setSecondName((noteDTO.getNoteSecondName()));
    repository.saveAndFlush(note);
  }

}
