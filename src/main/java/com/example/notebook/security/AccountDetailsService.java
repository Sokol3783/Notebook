package com.example.notebook.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountDetailsService {
  UserDetails loadAccountByLogin(String login) throws UsernameNotFoundException;
}
