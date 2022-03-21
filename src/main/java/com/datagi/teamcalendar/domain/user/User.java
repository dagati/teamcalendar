package com.datagi.teamcalendar.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public User(Long id, String email, String name, String password, Authority authority) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.authority = authority;
    }
}
