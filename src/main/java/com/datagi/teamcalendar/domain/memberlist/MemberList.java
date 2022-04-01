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
@Entity
public class MemberList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "team_id")
    @ManyToOne
    private Team team;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private User user;
}
