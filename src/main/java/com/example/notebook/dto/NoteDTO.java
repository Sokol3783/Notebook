package com.example.notebook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDTO {

  long noteId;
  String noteFirstName;
  String noteLastName;
  String noteSecondName;

  Long ownerId;

}
