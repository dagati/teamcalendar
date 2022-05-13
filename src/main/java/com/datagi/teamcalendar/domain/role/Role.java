package com.datagi.teamcalendar.domain.role;

import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Role extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 20)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
