package com.datagi.teamcalendar.domain.teamrelation;

import com.datagi.teamcalendar.domain.team.Team;
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
@Entity
public class TeamRelation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Team parent;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Team child;

    @NotNull
    @Column(columnDefinition = "tinyint")
    private int depth;
}
