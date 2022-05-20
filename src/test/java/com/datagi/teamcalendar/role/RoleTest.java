package com.datagi.teamcalendar.role;

import com.datagi.teamcalendar.domain.role.Role;
import com.datagi.teamcalendar.domain.role.repository.RoleRepository;
import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class RoleTest {

    private final TeamRepository teamRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleTest(
            TeamRepository teamRepository,
            RoleRepository roleRepository
    ) {
        this.teamRepository = teamRepository;
        this.roleRepository = roleRepository;
    }

    @Test
    @DisplayName("역할 생성 실패 테스트 (역할명 누락)")
    void createRoleFailureTest1() {
        Team team = teamRepository.getById(1L);

        Role role = Role.builder()
                .team(team)
                .build();

        Assertions.assertThatThrownBy(() -> roleRepository.save(role))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("역할 생성 실패 테스트 (팀 정보 없음)")
    void createRoleFailureTest2() {

        Role role = Role.builder()
                .name("professor")
                .build();

        Assertions.assertThatThrownBy(() -> roleRepository.save(role))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("역할 생성 성공 테스트")
    void createRoleSuccessTest() {

        Team team = teamRepository.getById(1L);

        Role role = Role.builder()
                .name("professor")
                .team(team)
                .build();
        roleRepository.save(role);

        Assertions.assertThat(role.getId()).isNotNull();
    }
}
