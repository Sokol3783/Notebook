package com.example.notebook.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/e/**", "/a/**").permitAll()
            .requestMatchers("/u/**").hasRole("USER")
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll())
        .csrf(s -> s.disable())
        .formLogin((form) -> form
            .loginPage("/a/home")
            .loginProcessingUrl("/a/home")
            .defaultSuccessUrl("/u/notebook")
            .permitAll())
        .logout( s -> s.invalidateHttpSession(true)
            .logoutSuccessUrl("/a/home"))
        .build();
  }

}
