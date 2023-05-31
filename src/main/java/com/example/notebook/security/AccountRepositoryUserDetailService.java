package com.example.notebook.security;

import com.example.notebook.entity.Account;
import com.example.notebook.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByLogin(username);
    if (account != null) {
      return account;
    }
    throw new UsernameNotFoundException("Account with" + username + " not found");
  }
}
