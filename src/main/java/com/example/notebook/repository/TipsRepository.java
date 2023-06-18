package com.example.notebook.repository;


import com.example.notebook.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipsRepository extends JpaRepository<Notice, Long> {

}

