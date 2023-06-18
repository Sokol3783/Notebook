package com.example.notebook.repository;

import com.example.notebook.entity.Note;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

  List<Note> findAllByOwnerId(Long ownerId);

  Page<Note> findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrSecondNameIgnoreCase(String keyword,
      Pageable pageable);

}

