package com.datagi.teamcalendar.domain.position;

import com.datagi.teamcalendar.domain.member.Member;
import com.datagi.teamcalendar.domain.role.Role;
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
        columnNames = {"role_id", "member_id"}
))
public class Position extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
