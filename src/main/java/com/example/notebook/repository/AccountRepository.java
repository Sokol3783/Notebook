package com.example.notebook.repository;


import com.example.notebook.entity.Acount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Acount, Long>,
    CrudRepository<Acount, Long> {

}

