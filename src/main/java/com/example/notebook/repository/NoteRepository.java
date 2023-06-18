package com.example.notebook.repository;

import com.example.notebook.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

  Page<Note> findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrSecondNameIgnoreCase(String keyword,
      Pageable pageable);

  Page<Note> findAllByOwnerId(Long id, Pageable pageable);
}

