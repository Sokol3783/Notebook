package com.example.notebook.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", nullable = false)
    private long id;

    @Column(name = "title", length = 30)
    String title;

    @Column(name = "content")
    String content;

    @ManyToOne
    @JoinColumn(name = "note_id", insertable = false, updatable = false)
    Note owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Notice notice = (Notice) o;
        return !Optional.of(getId()).isEmpty() && Objects.equals(getId(), notice.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
