package com.datagi.teamcalendar.domain.team;

import com.datagi.teamcalendar.domain.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 10)
    private String name;

    @OneToOne
    @JoinColumn(name = "leader_id")
    private User leader;
}
