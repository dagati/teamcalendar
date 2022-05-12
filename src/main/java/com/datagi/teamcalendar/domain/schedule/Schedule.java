package com.datagi.teamcalendar.domain.schedule;

import com.datagi.teamcalendar.domain.team.Team;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Schedule {

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

    private LocalDate endTime;

    @NotNull
    @Column(columnDefinition = "char(7) default '#333333'")
    private String color;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
