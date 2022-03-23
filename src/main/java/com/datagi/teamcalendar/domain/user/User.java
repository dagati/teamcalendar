package com.datagi.teamcalendar.domain.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 64)
    @Column(unique = true)
    private String email;

    @NotNull
    @Length(max = 10)
    private String name;

    @NotNull
    @Length(max = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Authority authority;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", authority=" + authority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(authority, user.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.email, this.name, this.password, this.authority);
    }
}
