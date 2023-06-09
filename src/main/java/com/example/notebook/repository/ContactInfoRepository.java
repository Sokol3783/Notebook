package com.example.notebook.repository;


import com.example.notebook.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
}

