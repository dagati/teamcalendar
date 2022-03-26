package com.datagi.teamcalendar.domain.schedule;

import com.datagi.teamcalendar.domain.team.Team;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Length(max =40)
    private String name;

    @Length(max = 2000)
    private String detail;

    @NotNull
    private LocalDateTime startDatetime;

    @NotNull
    private LocalDateTime endDatetime;

    @NotNull
    @Length(max =10)
    private String color;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;
}
