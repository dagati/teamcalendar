package com.datagi.teamcalendar.domain.schedule;

import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 20)
    private String name;

    @Length(max = 512)
    private String detail;

    @NotNull
    private LocalDate beginDate;

    private LocalTime beginTime;

    @NotNull
    private LocalDate endDate;

    private LocalTime endTime;

    @NotNull
    @Column(columnDefinition = "char(7) default '#333333'")
    private String color;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Schedule(Long id, String name, String detail,
                    LocalDate beginDate, LocalTime beginTime, LocalDate endDate, LocalTime endTime,
                    String color, Team team) {
        if (beginDate.isAfter(endDate)) {
            throw new IllegalArgumentException("The begin date must be after the end date");
        }
        if (beginDate.equals(endDate) && beginTime.isAfter(endTime)) {
            throw new IllegalArgumentException("The begin time must be after the end time");
        }

        this.id = id;
        this.name = name;
        this.detail = detail;
        this.beginDate = beginDate;
        this.beginTime = beginTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.color = color;
        this.team = team;
    }
}
