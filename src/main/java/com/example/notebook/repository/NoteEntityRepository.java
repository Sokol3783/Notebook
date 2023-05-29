package com.example.notebook.repository;

import com.example.notebook.entity.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteEntityRepository extends PagingAndSortingRepository<Note, Long>,
    CrudRepository<Note, Long> {

}

