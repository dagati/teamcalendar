package com.datagi.teamcalendar.role;

import com.datagi.teamcalendar.domain.role.Role;
import com.datagi.teamcalendar.domain.role.repository.RoleRepository;
import com.datagi.teamcalendar.domain.schedule.Schedule;
import com.datagi.teamcalendar.domain.schedule.repository.ScheduleRepository;
import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@Transactional
@SpringBootTest
class RoleTest {
    private final TeamRepository teamRepository;
    private final ScheduleRepository scheduleRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleTest(
            TeamRepository teamRepository,
            ScheduleRepository scheduleRepository,
            RoleRepository roleRepository
    ) {
        this.teamRepository = teamRepository;
        this.scheduleRepository = scheduleRepository;
        this.roleRepository = roleRepository;
    }

    @Test
    @DisplayName("역할 생성 성공 테스트")
    void createRoleSuccessTest() {

        Team team = teamRepository.getById(1L);
        Schedule schedule = scheduleRepository.getById(1L);

        Role role = Role.builder()
                .name("professor")
                .team(team)
                .schedule(schedule)
                .build();
        roleRepository.save(role);

        Assertions.assertThat(role.getId()).isNotNull();
    }

    @Test
    @DisplayName("역할 생성 실패 테스트 (팀 정보 없음)")
    void createRoleFailureTest1() {
        Schedule schedule = scheduleRepository.getById(1L);

        Role role = Role.builder()
                .name("professor")
                .schedule(schedule)
                .build();

        Assertions.assertThatThrownBy(() -> roleRepository.save(role))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("역할 생성 실패 테스트 (일정 정보 없음)")
    void createRoleFailureTest2() {
        Team team = teamRepository.getById(1L);

        Role role = Role.builder()
                .name("professor")
                .team(team)
                .build();

        Assertions.assertThatThrownBy(() -> roleRepository.save(role))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("역할 생성 실패 테스트 (역할명 누락)")
    void createRoleFailureTest3() {
        Team team = teamRepository.getById(1L);
        Schedule schedule = scheduleRepository.getById(1L);

        Role role = Role.builder()
                .team(team)
                .schedule(schedule)
                .build();

        Assertions.assertThatThrownBy(() -> roleRepository.save(role))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
