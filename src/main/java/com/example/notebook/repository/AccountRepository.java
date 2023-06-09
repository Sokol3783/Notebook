package com.example.notebook.repository;


import com.example.notebook.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByLogin(String login);
}

