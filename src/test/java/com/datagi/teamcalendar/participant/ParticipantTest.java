package com.datagi.teamcalendar.participant;

import com.datagi.teamcalendar.domain.participant.Participant;
import com.datagi.teamcalendar.domain.participant.repository.ParticipantRepository;
import com.datagi.teamcalendar.domain.schedule.Schedule;
import com.datagi.teamcalendar.domain.schedule.repository.ScheduleRepository;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class ParticipantTest {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantTest(UserRepository userRepository,
                           ScheduleRepository scheduleRepository,
                           ParticipantRepository participantRepository
    ) {
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.participantRepository = participantRepository;
    }

    @Test
    @DisplayName("참가자 저장 성공 테스트")
    void saveParticipantSuccessTest() {

        User user = userRepository.getById(1L);
        Schedule schedule = scheduleRepository.getById(1L);

        Participant participant = Participant.builder()
                .user(user)
                .schedule(schedule)
                .build();

        participantRepository.save(participant);

        assertThat(participant.getId()).isNotNull();
    }

    @Test
    @DisplayName("참가자 저장 실패 테스트 (유저가 없음)")
    void saveParticipantFailureTest1() {

        Schedule schedule = scheduleRepository.getById(1L);

        Participant participant = Participant.builder()
                .schedule(schedule)
                .build();

        assertThatThrownBy(() -> participantRepository.save(participant))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("참가자 저장 실패 테스트 (일정이 없음)")
    void saveParticipantFailureTest2() {

        User user = userRepository.getById(1L);

        Participant participant = Participant.builder()
                .user(user)
                .build();

        assertThatThrownBy(() -> participantRepository.save(participant))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
