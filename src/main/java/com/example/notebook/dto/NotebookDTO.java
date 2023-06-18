package com.example.notebook.dto;


import com.example.notebook.entity.Note;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotebookDTO {
  Long owner_id;
  List<Note> notes;
  String firstName;
  String lastName;
  String secondName;

  String keyword;

  String message;


  int pageSize = 10;

  int pageNumber = 1;

  List<Integer> sizesOfPage = List.of(5, 10, 15, 20);
  List<String> sort = List.of("id", "asc");
}
