package com.example.notebook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "notice_type")
public class NoticeType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notice_type_id", nullable = false)
  private Long id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "owner_account_id", referencedColumnName = "account_id")
  private Account owner;

  enum DefaultTypeTipsInfo {
    POSITION,
    SCHOOL,
    OTHER
  }

}
