package com.example.notebook.repository;

import com.example.notebook.entity.NoticeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TypeTipsRepository extends PagingAndSortingRepository<NoticeType, Long>,
    CrudRepository<NoticeType, Long> {
}
