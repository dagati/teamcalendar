package com.datagi.teamcalendar.domain.rolelist;

import com.datagi.teamcalendar.domain.role.Role;
import com.datagi.teamcalendar.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class RoleList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
