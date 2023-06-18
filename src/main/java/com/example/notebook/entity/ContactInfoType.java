package com.example.notebook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "contact_info_type")
public class ContactInfoType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contact_info_type_id", nullable = false)
  private Long id;

  @Column(name = "contact_info_name", nullable = false, unique = true)
  private String name;


  @ManyToOne
  @JoinColumn(name = "account_id", referencedColumnName = "account_id")
  private Account owner;


  public enum ContactInfoTypeDefault {
    WORK_PHONE("work phone"),
    PHONE("phone"),
    EMAIL("email");

    ContactInfoTypeDefault(String name) {
    }
  }
}
