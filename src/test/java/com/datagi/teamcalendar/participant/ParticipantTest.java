package com.datagi.teamcalendar.participant;

import com.datagi.teamcalendar.domain.member.Member;
import com.datagi.teamcalendar.domain.member.repository.MemberRepository;
import com.datagi.teamcalendar.domain.participant.Participant;
import com.datagi.teamcalendar.domain.participant.repository.ParticipantRepository;
import com.datagi.teamcalendar.domain.schedule.Schedule;
import com.datagi.teamcalendar.domain.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ParticipantTest {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantTest(
            ScheduleRepository scheduleRepository,
            MemberRepository memberRepository,
            ParticipantRepository participantRepository
    ) {
        this.scheduleRepository = scheduleRepository;
        this.memberRepository = memberRepository;
        this.participantRepository = participantRepository;
    }

    @Test
    @DisplayName("참가자 저장 실패 테스트 (팀원 정보 누락)")
    void saveParticipantFailureTest1() {

        Schedule schedule = scheduleRepository.getById(1L);

        Participant participant = Participant.builder()
                .schedule(schedule)
                .build();

        assertThatThrownBy(() -> participantRepository.save(participant))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("참가자 저장 실패 테스트 (일정 정보 누락)")
    void saveParticipantFailureTest2() {

        Member member = memberRepository.getById(1L);

        Participant participant = Participant.builder()
                .member(member)
                .build();

        assertThatThrownBy(() -> participantRepository.save(participant))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("참가자 저장 실패 테스트 (이미 존재하는 참가자)")
    void saveParticipantFailureTest3() {

        Schedule schedule = scheduleRepository.getById(1L);
        Member member = memberRepository.getById(1L);

        Participant participant = Participant.builder()
                .member(member)
                .schedule(schedule)
                .build();

        assertThatThrownBy(() -> participantRepository.save(participant))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("참가자 저장 성공 테스트")
    void saveParticipantSuccessTest() {

        Schedule schedule = scheduleRepository.getById(1L);
        Member member = memberRepository.getById(2L);

        Participant participant = Participant.builder()
                .member(member)
                .schedule(schedule)
                .build();

        participantRepository.save(participant);

        assertThat(participant.getId()).isNotNull();
    }
}
