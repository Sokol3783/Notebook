package com.example.notebook.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account implements UserDetails {
    @Serial
    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    long id;

    @Column(name="first_name", length = 50)
    String firstName;

    @Column(name = "last_name", length = 50)
    String lastName;

    @Email(message = "enter valid e-mail")
    @NotBlank(message = "e-mail should not be blank")
    @Column(name = "email", unique = true, length = 100)
    String email;

    @Column(name = "password")
    String password;

    @NotBlank(message = "login should not be blank")
    @Column(name = "login", unique = true)
    String login;
    @Pattern(message = "Can register only by Ukrainian number!", regexp = "^\\+380\\d{9}$\n")
    @Column(name = "phone", unique = true, length = 13)
    String phone;
    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_account_id", referencedColumnName = "account_id")
    @Exclude
    List<Note> notes;

    public void setPassword(String password, @Autowired PasswordEncoder passwordEncoder) {
        String hashedPassword = passwordEncoder.encode(password);
        this.password = hashedPassword;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Account account = (Account) o;
        return Optional.of(getId()).isPresent() && Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
