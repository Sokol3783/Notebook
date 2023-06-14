package com.example.notebook.security;

import com.example.notebook.entity.Account;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountDetailsService {
  Account authorizeUser(String login, CharSequence password) throws UsernameNotFoundException;
}
