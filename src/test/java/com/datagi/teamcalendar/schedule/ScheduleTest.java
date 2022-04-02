package com.datagi.teamcalendar.schedule;

import com.datagi.teamcalendar.domain.schedule.Schedule;
import com.datagi.teamcalendar.domain.schedule.repository.ScheduleRepository;
import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import com.datagi.teamcalendar.domain.user.Authority;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class ScheduleTest {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleTest(UserRepository userRepository,
                        TeamRepository teamRepository,
                        ScheduleRepository scheduleRepository
    ) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Test
    @DisplayName("일정 저장 성공 테스트")
    void saveScheduleSuccessTest() {

        Team team = makeTeam();

        Schedule schedule = Schedule.builder()
                .name("Grilled Fish")
                .detail("go to eat Grilled Fish")
                .startDatetime(LocalDateTime.of(2022, 5, 3, 12, 0))
                .endDatetime(LocalDateTime.of(2022, 5, 3, 13, 0))
                .color("#000000")
                .team(team)
                .build();

        scheduleRepository.save(schedule);

        Schedule result = scheduleRepository.findById(schedule.getId()).orElse(null);

        assertThat(result).isEqualTo(schedule);
    }

    @Test
    @DisplayName("일정 저장 실패 테스트 (일정 제목이 없음)")
    void saveScheduleFailureTest1() {

        Team team = makeTeam();

        Schedule schedule = Schedule.builder()
                .detail("go to eat Grilled Fish")
                .startDatetime(LocalDateTime.of(2022, 5, 3, 12, 0))
                .endDatetime(LocalDateTime.of(2022, 5, 3, 13, 0))
                .color("#000000")
                .team(team)
                .build();

        assertThatThrownBy(() -> scheduleRepository.save(schedule))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("일정 저장 실패 테스트 (일정 제목이 제한을 넘음)")
    void saveScheduleFailureTest2() {

        Team team = makeTeam();

        Schedule schedule = Schedule.builder()
                .name("I like Fish and Milk so let's go~~ and I am happy")
                .detail("go to eat Grilled Fish")
                .startDatetime(LocalDateTime.of(2022, 5, 3, 12, 0))
                .endDatetime(LocalDateTime.of(2022, 5, 3, 13, 0))
                .color("#000000")
                .team(team)
                .build();

        assertThatThrownBy(() -> scheduleRepository.save(schedule))
                .isInstanceOf(ConstraintViolationException.class);
    }

    private Team makeTeam() {
        User user = User.builder()
                .email("kim@gmail.com")
                .password("kim123")
                .name("Champon")
                .authority(Authority.USER)
                .build();
        userRepository.save(user);

        Team team = Team.builder()
                .name("kick")
                .leader(user)
                .build();
        teamRepository.save(team);

        return team;
    }
}
