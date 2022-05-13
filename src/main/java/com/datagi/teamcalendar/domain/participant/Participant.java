package com.datagi.teamcalendar.domain.participant;

import com.datagi.teamcalendar.domain.member.Member;
import com.datagi.teamcalendar.domain.schedule.Schedule;
import com.datagi.teamcalendar.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"schedule_id", "member_id"}
))
public class Participant extends BaseTimeEntity {

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
