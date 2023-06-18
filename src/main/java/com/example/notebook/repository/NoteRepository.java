package com.example.notebook.repository;

import com.example.notebook.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoteRepository extends JpaRepository<Note, Long> {


  Page<Note> findAllByOwnerId(Long id, Pageable pageable);

  @Query(value = """
      SELECT * FROM note
      RIGHT JOIN account ON note.owner_account_id = account.account_id
      WHERE account.account_id = :id
      AND (note.first_name LIKE CONCAT('%', :keyword)
          OR note.second_name LIKE CONCAT('%', :keyword)
          OR note.last_name LIKE CONCAT('%', :keyword))

      """,
      countQuery = """
          SELECT count(note.note_id)  FROM note
          RIGHT JOIN account ON note.owner_account_id = account.account_id
          WHERE account.account_id = :id
          AND (note.first_name LIKE CONCAT('%', :keyword)
              OR note.second_name LIKE CONCAT('%', :keyword)
              OR note.last_name LIKE CONCAT('%', :keyword))

          """
  ,nativeQuery = true)
  Page<Note> findByKeyword(String keyword, Pageable pageable, Long id);
}

