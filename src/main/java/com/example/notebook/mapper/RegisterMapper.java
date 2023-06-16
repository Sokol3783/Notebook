package com.example.notebook.mapper;

import com.example.notebook.dto.RegisterDTO;
import com.example.notebook.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterMapper {

  private final PasswordEncoder encoder;

  public RegisterMapper(@Autowired PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  public Account mapping(RegisterDTO register) {
    Account account = new Account();
    account.setLogin(register.getLogin());
    account.setFirstName(register.getFirstName());
    account.setLastName(register.getLastName());
    account.setEmail(register.getEmail());
    account.setPassword(register.getPassword().toString(), encoder);
    account.setPhone(register.getPhone());
    //account.setRole("ROLE_USER");
    return account;
  }
}
