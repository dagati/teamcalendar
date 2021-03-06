package com.datagi.teamcalendar.domain.invitationcode;

import com.datagi.teamcalendar.domain.team.Team;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"token"}
))
@Entity
public class InvitationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 16)
    private String token;

    @JoinColumn(name = "team_id")
    @OneToOne
    private Team team;
}
