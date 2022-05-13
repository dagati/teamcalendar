package com.datagi.teamcalendar.domain.participant;

import com.datagi.teamcalendar.domain.member.Member;
import com.datagi.teamcalendar.domain.schedule.Schedule;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
