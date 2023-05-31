package com.example.notebook.repository;


import com.example.notebook.entity.Notice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TipsRepository extends PagingAndSortingRepository<Notice, Long>, CrudRepository<Notice, Long> {
}

