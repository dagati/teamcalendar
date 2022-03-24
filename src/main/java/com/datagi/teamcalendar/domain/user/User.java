package com.datagi.teamcalendar.domain.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
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
}
