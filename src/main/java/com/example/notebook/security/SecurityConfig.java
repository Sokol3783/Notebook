package com.example.notebook.security;

import com.example.notebook.entity.Account;
import com.example.notebook.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(AccountRepository userRepo) {
    return username -> {
      Account user = userRepo.findByLogin(username);
      if (user != null) {
        return user;
      }
      throw new UsernameNotFoundException("User ‘" + username + "’ not found");
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    /*
    http.authorizeHttpRequests(ao -> ao.requestMatchers("/home", "/registry").permitAll());
    http.authorizeHttpRequests(ao -> ao.requestMatchers("/error/").permitAll());
    http.authorizeHttpRequests(ao -> ao.requestMatchers(RegexRequestMatcher.regexMatcher("/resource/[A-Za-z0-9]")).permitAll());
    http.authorizeHttpRequests(ao -> ao.requestMatchers("/pages/").hasRole("USER").anyRequest().authenticated());
    http.formLogin(s -> s.loginPage("/home").permitAll());
    http.logout(s -> s.logoutSuccessUrl("/home"));
     */

    http.authorizeHttpRequests(ao -> ao.anyRequest().permitAll());
    return http.build();
  }
}
