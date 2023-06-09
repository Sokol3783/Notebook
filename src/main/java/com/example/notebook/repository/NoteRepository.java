package com.example.notebook.repository;

import com.example.notebook.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long>{

}

