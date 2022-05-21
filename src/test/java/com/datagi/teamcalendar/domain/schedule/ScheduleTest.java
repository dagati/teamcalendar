package com.datagi.teamcalendar.domain.schedule;

import com.datagi.teamcalendar.domain.schedule.repository.ScheduleRepository;
import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ScheduleTest {

    private final TeamRepository teamRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleTest(
            TeamRepository teamRepository,
            ScheduleRepository scheduleRepository
    ) {
        this.teamRepository = teamRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Test
    @DisplayName("일정 저장 실패 테스트 (일정 제목 누락)")
    void saveScheduleFailureTest1() {

        Team team = teamRepository.getById(1L);

        Schedule schedule = Schedule.builder()
                .detail("go to eat Grilled Fish")
                .beginDate(LocalDate.of(2022, 5, 15))
                .beginTime(LocalTime.of(12, 0))
                .endDate(LocalDate.of(2022, 5, 15))
                .endTime(LocalTime.of(13, 0))
                .color("#000000")
                .team(team)
                .build();

        assertThatThrownBy(() -> scheduleRepository.save(schedule))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("일정 저장 실패 테스트 (일정 제목이 제한을 넘음)")
    void saveScheduleFailureTest2() {

        Team team = teamRepository.getById(1L);

        Schedule schedule = Schedule.builder()
                .name("I like Fish and Milk so let's go~~ and I am happy")
                .detail("go to eat Grilled Fish")
                .beginDate(LocalDate.of(2022, 5, 15))
                .beginTime(LocalTime.of(12, 0))
                .endDate(LocalDate.of(2022, 5, 15))
                .endTime(LocalTime.of(13, 0))
                .color("#000000")
                .team(team)
                .build();

        assertThatThrownBy(() -> scheduleRepository.save(schedule))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("일정 저장 실패 테스트 (시작 날짜가 끝 날짜보다 앞섬)")
    void saveScheduleFailureTest3() {

        Team team = teamRepository.getById(1L);

        Schedule.ScheduleBuilder scheduleBuilder = Schedule.builder()
                .name("Grilled Fish")
                .detail("go to eat Grilled Fish")
                .beginDate(LocalDate.of(2022, 5, 15))
                .beginTime(LocalTime.of(12, 0))
                .endDate(LocalDate.of(2022, 5, 14))
                .endTime(LocalTime.of(13, 0))
                .color("#000000")
                .team(team);

        assertThatThrownBy(scheduleBuilder::build)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("일정 저장 실패 테스트 (시작 시간이 끝 시간보다 앞섬)")
    void saveScheduleFailureTest4() {

        Team team = teamRepository.getById(1L);

        Schedule.ScheduleBuilder scheduleBuilder = Schedule.builder()
                .name("Grilled Fish")
                .detail("go to eat Grilled Fish")
                .beginDate(LocalDate.of(2022, 5, 15))
                .beginTime(LocalTime.of(12, 0))
                .endDate(LocalDate.of(2022, 5, 15))
                .endTime(LocalTime.of(10, 0))
                .color("#000000")
                .team(team);

        assertThatThrownBy(scheduleBuilder::build)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("일정 저장 성공 테스트")
    void saveScheduleSuccessTest() {

        Team team = teamRepository.getById(1L);

        Schedule schedule = Schedule.builder()
                .name("Grilled Fish")
                .detail("go to eat Grilled Fish")
                .beginDate(LocalDate.of(2022, 5, 15))
                .beginTime(LocalTime.of(12, 0))
                .endDate(LocalDate.of(2022, 5, 15))
                .endTime(LocalTime.of(13, 0))
                .color("#000000")
                .team(team)
                .build();

        scheduleRepository.save(schedule);

        assertThat(schedule.getId()).isNotNull();
    }
}
