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

  PaginationProperties page = new PaginationProperties();


  @Getter
  @Setter
  @NoArgsConstructor
  public static class PaginationProperties {

    private int pageSize = 10;

    private int currentPage = 1;
    private int total;

    private List<Integer> sizesOfPage = List.of(5, 10, 15, 20);
    private String sortField = "id";
    private String sortDirection = "asc";

  }

}
