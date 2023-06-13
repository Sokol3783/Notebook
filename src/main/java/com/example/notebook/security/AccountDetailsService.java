package com.example.notebook.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountDetailsService {
  UserDetails authorizeUser(String login, CharSequence password) throws UsernameNotFoundException;
}
