package com.example.notebook.repository;


import com.example.notebook.entity.Tips;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TipsRepository extends PagingAndSortingRepository<Tips, Long>, CrudRepository<Tips, Long> {
}

