package com.example.notebook.entity;

import com.example.notebook.entity.ContactInfoType.ContactInfoTypeDefault;
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
@Table(name = "contact_info")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_info_id", nullable = false)
    private Long id;

    @Column(name = "contact_info_name", nullable = false)
    private String name;


    /* TODO first make default for enum than make crud operations with types for each user
    @ManyToOne
    @JoinColumn(name = "contact_info_type_id",  referencedColumnName ="contact_info_type_id", nullable = false)
    private ContactInfoType type;
     */

    @ManyToOne
    @JoinColumn(name = "owner_note_id", referencedColumnName = "note_id")
    private Note owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_info_type")
    private ContactInfoTypeDefault type;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        ContactInfo that = (ContactInfo) o;
        return Optional.of(getId()).isPresent() && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
