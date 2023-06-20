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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  AuthenticationSuccessHandler authenticationSuccessHandler;

  public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
    this.authenticationSuccessHandler = authenticationSuccessHandler;
  }


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
            .requestMatchers("/", "/error/**", "/anonymous-pages/**", "/a/**").permitAll()
            .requestMatchers("/u/**", "/u/notebook/**").hasRole("USER")
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll())
        .csrf(s -> s.disable())
        .formLogin((form) -> form
            .loginPage("/a/home")
            .loginProcessingUrl("/a/home")
            .defaultSuccessUrl("/u/notebook")
            .permitAll()
            .successHandler(authenticationSuccessHandler)
        )
        .logout(s -> s.invalidateHttpSession(true)
            .logoutSuccessUrl("/a/home"))
        .build();
  }


}
