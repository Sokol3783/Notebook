package com.example.notebook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "type_contact_info")
public class TypeContactInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  private String name;

  enum DefaultTypeContactInfo {

    PHONE(1, "Phone"),
    MAIL(2, "Mail");

    DefaultTypeContactInfo(int i, String mail) {
    }
  }

}
