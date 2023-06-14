package com.example.notebook.security;

import com.example.notebook.entity.Account;
import com.example.notebook.repository.AccountRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("AccountDetailsService")
public class AccountRepositoryUserDetailService implements AccountDetailsService {

  private final AccountRepository accountRepository;
  private final PasswordEncoder encoder;

  public AccountRepositoryUserDetailService(AccountRepository accountRepository,
      PasswordEncoder encoder) {
    this.accountRepository = accountRepository;
    this.encoder = encoder;
  }

  @Override
  public Account authorizeUser(String username, CharSequence password)
      throws UsernameNotFoundException {
    Account account = accountRepository.findByLogin(username);
    if (account != null) {
      if (encoder.matches(password, account.getPassword())) {
        return account;
      }
    }
    throw new UsernameNotFoundException("Account with" + username + " not found");
  }

}
