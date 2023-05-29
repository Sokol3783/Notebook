package com.example.notebook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "type_tips_info")
public class TypeTips {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "type_tips_info_id", nullable = false)
  private Long id;
  private String name;

  @ManyToOne
  private Account owner;

  enum DefaultTypeTipsInfo {
    POSITION,
    SCHOOL,
    OTHER
  }

}
