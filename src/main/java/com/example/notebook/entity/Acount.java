package com.example.notebook.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Acount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    long id;

    @NotBlank(message ="name should not be blank")
    String firstName;

    @NotBlank(message ="first name should not be blank")
    String lastName;

    @NotBlank(message ="first name should not be blank")
    String email;

    @NotBlank(message ="password should not be blank")
    String password;

    @NotBlank(message ="login should not be blank")
    String login;
    @NotBlank(message ="phone should not be blank")
    String phone;
    @OneToMany
    List<Note> noteEntities;
    @OneToMany
    List<Tips> tips;
}
