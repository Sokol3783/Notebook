package com.example.notebook.repository;


import com.example.notebook.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long>,
    CrudRepository<Account, Long> {
    Account findByLogin(String login);
}

