package com.example.notebook.repository;

import com.example.notebook.entity.TypeTips;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TypeTipsRepository extends PagingAndSortingRepository<TypeTips, Long>,
    CrudRepository<TypeTips, Long> {
}
