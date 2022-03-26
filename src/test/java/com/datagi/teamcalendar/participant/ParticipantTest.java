package com.datagi.teamcalendar.participant;

import com.datagi.teamcalendar.domain.participant.Participant;
import com.datagi.teamcalendar.domain.participant.repository.ParticipantRepository;
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
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ParticipantTest {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ScheduleRepository scheduleRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantTest(UserRepository userRepository, TeamRepository teamRepository, ScheduleRepository scheduleRepository, ParticipantRepository participantRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.scheduleRepository = scheduleRepository;
        this.participantRepository = participantRepository;
    }

    @Test
    @DisplayName("참가자 저장 성공 테스트")
    void saveParticipantSuccessTest() {

        User user = makeUser();
        Schedule schedule = makeSchedule();

        Participant participant = Participant.builder()
                .user(user)
                .schedule(schedule)
                .build();

        participantRepository.save(participant);

        Participant result = participantRepository.findById(participant.getId()).orElse(null);

        assertThat(result).isEqualTo(participant);
    }

    private Schedule makeSchedule() {
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

        Schedule schedule = Schedule.builder()
                .name("Grilled Fish")
                .detail("go to eat Grilled Fish")
                .startDatetime(LocalDateTime.of(2022, 5, 3, 12, 0))
                .endDatetime(LocalDateTime.of(2022, 5, 3, 13, 0))
                .color("#000000")
                .team(team)
                .build();
        scheduleRepository.save(schedule);
        return schedule;
    }

    private User makeUser() {
        User user = User.builder()
                .email("kkk3@gmail.com")
                .password("kkk123")
                .name("cocoball")
                .authority(Authority.USER)
                .build();
        userRepository.save(user);

        return user;
    }
}
