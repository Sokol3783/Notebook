package com.example.notebook.entity;

import com.example.notebook.entity.NoticeType.TypeNoticeDefault;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "notice_type")
    TypeNoticeDefault type;

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
