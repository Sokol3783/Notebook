package com.example.notebook.repository;

import com.example.notebook.entity.NoticeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeTipsRepository extends JpaRepository<NoticeType, Long> {
}
