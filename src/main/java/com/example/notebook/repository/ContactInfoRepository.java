package com.example.notebook.repository;


import com.example.notebook.entity.ContactInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactInfoRepository extends PagingAndSortingRepository<ContactInfo, Long>, CrudRepository<ContactInfo, Long> {
}

