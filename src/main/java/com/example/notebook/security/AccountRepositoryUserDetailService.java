package com.example.notebook.security;

import com.example.notebook.entity.Account;
import com.example.notebook.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountRepositoryUserDetailService implements UserDetailsService {

  private final AccountRepository accountRepository;
  @Autowired
  public AccountRepositoryUserDetailService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Bean
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
      Account account = accountRepository.findByLogin(login);
      if (account != null) {
        return account;
      }
      throw new UsernameNotFoundException("Account with" + login + " not found");
    }
}
}
