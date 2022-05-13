package com.datagi.teamcalendar.domain.user;

import com.datagi.teamcalendar.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 80)
    @Column(unique = true)
    private String email;

    @NotNull
    @Length(max = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Authority authority;
}
