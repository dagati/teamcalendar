package com.datagi.teamcalendar.domain.memberlist;

import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"team_id", "user_id"}
        )
)
@Entity
public class MemberList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "team_id")
    @ManyToOne
    private Team team;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
}
