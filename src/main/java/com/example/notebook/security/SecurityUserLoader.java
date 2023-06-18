package com.example.notebook.security;

import com.example.notebook.entity.Account;
import com.example.notebook.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class SecurityUserLoader implements UserDetailsService {

  private final AccountRepository accountRepository;

  public SecurityUserLoader(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account loadedUser = accountRepository.findByLogin(username).
        orElseThrow(() -> new UsernameNotFoundException("Could not found a user with given name"));
    return new org.springframework.security.core.userdetails.User(
        loadedUser.getUsername(),
        loadedUser.getPassword(),
        true,
        true,
        true,
        true,
        loadedUser.getAuthorities()
    );
  }
}
