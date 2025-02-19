package com.example.notebook.mapper;

import com.example.notebook.dto.NoteDTO;
import com.example.notebook.entity.Account;
import com.example.notebook.entity.Note;
import com.example.notebook.entity.Note.NoteBuilder;
import com.example.notebook.repository.AccountRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NoteMapper {

  private final AccountRepository repository;


  public NoteMapper(AccountRepository repository) {
    this.repository = repository;
  }

  public Note mapToObject(NoteDTO note) {
    NoteBuilder builder = new Note().toBuilder();
    builder.owner(getOwner(note));
    builder.firstName(note.getNoteFirstName());
    builder.lastName(note.getNoteLastName());
    builder.secondName(note.getNoteSecondName());
    if(note.getNoteId().length() > 0){
      builder.id(Long.parseLong(note.getNoteId()));
    }

    return builder.build();
  }

  private Account getOwner(NoteDTO note) {
    Optional<Account> byId = repository.findById(note.getOwnerId());
    if (byId.isEmpty()) {
      throw new UsernameNotFoundException("User with id " + note.getOwnerId() + " not found");
    }
    return byId.get();
  }

}
