package com.datagi.teamcalendar.domain.member;

import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(
                columnNames = {"team_id", "user_id"}))
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
