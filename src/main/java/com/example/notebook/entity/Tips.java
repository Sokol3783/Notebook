package com.example.notebook.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tips")
public class Tips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tips_id", nullable = false)
    private long id;
    String title;
    String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Tips tips = (Tips) o;
        return !Optional.of(getId()).isEmpty() && Objects.equals(getId(), tips.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
