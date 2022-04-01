package com.datagi.teamcalendar.domain.invitationcode;

import com.datagi.teamcalendar.domain.team.Team;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 20)
    private String token;

    @JoinColumn(name = "team_id")
    @OneToOne
    private Team team;
}
